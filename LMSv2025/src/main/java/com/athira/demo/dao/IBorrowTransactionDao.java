package com.athira.demo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.BorrowTransaction;

@Repository
public interface IBorrowTransactionDao extends JpaRepository<BorrowTransaction, Integer> {

	@Procedure(name = "sp_LogBorrowingAndUpdateAvailableCopies")
	void sp_LogBorrowingAndUpdateAvailableCopies(Integer bookId, Integer memberId, Timestamp timestamp);

	@Query("from BorrowTransaction WHERE memberId = :id")
	List<BorrowTransaction> findByMemberId(@Param("id") int id);

	@Query("from BorrowTransaction WHERE bookId = :id")
	List<BorrowTransaction> findByBookId(@Param("id") int id);

	@Procedure(name = "sp_ReturnLogsAndUpdateAvailableCopies")
	void sp_ReturnLogsAndUpdateAvailableCopies(Integer getbTransId, Integer bookId, Timestamp timestamp,
			String returnStatus);

}
