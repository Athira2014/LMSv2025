package com.athira.demo.service;

import java.util.List;
import java.util.Optional;
import com.athira.demo.entity.BorrowTransaction;

public interface IBorrowTransactionService {

	List<BorrowTransaction> getAllBorrowTransaction();

	Optional<BorrowTransaction> getBorrowTransactionById(Integer id);

	List<BorrowTransaction> getBorrowTransactionByMemberId(Integer id);

	List<BorrowTransaction> getBorrowTransactionByBookId(Integer id);

	void saveBorrowTransaction(BorrowTransaction borrowTransaction);

	void updateBorrowTransaction(BorrowTransaction borrowTransaction);

}
