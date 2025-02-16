package com.athira.demo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.athira.demo.dao.IBorrowTransactionDao;
import com.athira.demo.entity.BorrowTransaction;

@Transactional
@Service
public class BorrowTransactionService implements IBorrowTransactionService {

	@Autowired
	IBorrowTransactionDao borrowTransactionDao;

	@Autowired
	ExecutorService executor;

	@Transactional
	public List<BorrowTransaction> getAllBorrowTransaction() {
		return borrowTransactionDao.findAll();
	}

	@Transactional
	public Optional<BorrowTransaction> getBorrowTransactionById(Integer id) {
		return borrowTransactionDao.findById(id);
	}

	public List<BorrowTransaction> getBorrowTransactionByMemberId(Integer id) {
		return borrowTransactionDao.findByMemberId(id);
	}

	public List<BorrowTransaction> getBorrowTransactionByBookId(Integer id) {
		return borrowTransactionDao.findByBookId(id);
	}

	// @Transactional

	/*
	 * Approach 1
	 * 
	 * @Async : - Using @Async and @Transactional for Asynchronous Processing
	 * Spring's @Async can handle asynchronous processing, but it
	 * requires @EnableAsync to be configured in the Spring configuration class.
	 */
	public void saveBorrowTransaction(BorrowTransaction borrowTransaction) {

		// Approach 1:
		try {

			// input validation
			if (borrowTransaction.getBookId() == null || borrowTransaction.getMemberId() == null) {
				throw new IllegalArgumentException("Book id and member id should not be null.");
			}
			Timestamp timestamp = new Timestamp(borrowTransaction.getBorrowDate().getMillis());
			
			 borrowTransactionDao.sp_LogBorrowingAndUpdateAvailableCopies(borrowTransaction.getBookId(),
					borrowTransaction.getMemberId(), timestamp);

		} catch (IllegalArgumentException e) {
			// invalid argument error
			throw new IllegalArgumentException("Invalid data: " + e.getMessage(), e);
		} catch (DataAccessException e) {
			// handling data access issue
			throw new RuntimeException("Error accessing database during transaction save", e);
		} catch (Exception e) {
			// handling unexpected exception
			throw new RuntimeException("Unexpected error occurred while saving transaction", e);
		}

		/*
		 * Approach 2: Handling Exception and Results Asynchronously return
		 * CompletableFuture.supplyAsync(() -> { try { if(borrowTransaction.getBookId()
		 * == null || borrowTransaction.getMemberId() == null) { throw new
		 * IllegalArgumentException("Book id and member id should not be null."); }
		 * borrowTransactionDao.createBorrowTransaction(borrowTransaction.getBookId(),
		 * borrowTransaction.getMemberId(), borrowTransaction.getBorrowDate()); return
		 * borrowTransactionDao.save(borrowTransaction); } catch
		 * (IllegalArgumentException e) { throw new
		 * IllegalArgumentException("Invalid data: " + e.getMessage(), e); } catch
		 * (DataAccessException e) { throw new
		 * RuntimeException("Error accessing database during transaction save", e); }
		 * catch (Exception e) { throw new
		 * RuntimeException("Unexpected error occurred while saving transaction", e); }
		 * }, executor);
		 */
	}

	@Async
	public void updateBorrowTransaction(BorrowTransaction borrowTransaction) {

		try {

			if (borrowTransaction.getbTransId() == null || borrowTransaction.getBookId() == null
					|| borrowTransaction.getReturnDate() == null || borrowTransaction.getReturnStatus() == null) {
				throw new IllegalArgumentException("Input fields should not be empty.");
			}
			Timestamp timestamp = new Timestamp(borrowTransaction.getReturnDate().getMillis());

			borrowTransactionDao.sp_ReturnLogsAndUpdateAvailableCopies(borrowTransaction.getbTransId(),
					borrowTransaction.getBookId(),timestamp,
					borrowTransaction.getReturnStatus());

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid data: " + e.getMessage(), e);
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Error accessing database during transaction save", e);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occurred while updating transaction", e);
		}
	}

}
