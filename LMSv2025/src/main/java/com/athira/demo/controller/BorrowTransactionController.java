package com.athira.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.BorrowTransaction;
import com.athira.demo.service.IBorrowTransactionService;

@RestController
@RequestMapping("api/")
public class BorrowTransactionController {

	@Autowired
	IBorrowTransactionService borrowTransactionService;
	
	@GetMapping("transactions")
	public List<BorrowTransaction> getAllBorrowTransaction() {
		return borrowTransactionService.getAllBorrowTransaction();
	}
	
	@GetMapping("transactions/{id}")
	public Optional<BorrowTransaction> getBorrowTransactionById(@PathVariable Integer id) {
		return borrowTransactionService.getBorrowTransactionById(id);
	}

	
	@GetMapping("transactions/member/{id}")
	public List<BorrowTransaction> getBorrowTransactionByMemberId(@PathVariable Integer id) {
		return borrowTransactionService.getBorrowTransactionByMemberId(id);
	}
	
	
	@GetMapping("transactions/books/{id}")
	public List<BorrowTransaction> getBorrowTransactionByBookId(@PathVariable Integer id) {
		return borrowTransactionService.getBorrowTransactionByBookId(id);
	}
	
	@PostMapping("transactions/save")
	public ResponseEntity<APIResponse> saveBorrowTransaction(@RequestBody BorrowTransaction borrowTransaction) {

		APIResponse apiResponse = new APIResponse();
		try {
			borrowTransactionService.saveBorrowTransaction(borrowTransaction);
			apiResponse.setStatus(200);
			apiResponse.setData("Transaction created succesfully");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		
	}
	
	@PostMapping("transactions/update")
	public ResponseEntity<APIResponse> updateBorrowTransaction(@RequestBody BorrowTransaction borrowTransaction) {

		APIResponse apiResponse = new APIResponse();
		try {
			 borrowTransactionService.updateBorrowTransaction(borrowTransaction);
			apiResponse.setStatus(200);
			apiResponse.setData("Transaction updated succesfully");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		
	}
	/*
	 * @PostMapping("transactions") public CompletableFuture<BorrowTransaction>
	 * createBorrowTransaction(@RequestBody BorrowTransaction borrowTransaction) {
	 * return borrowTransactionService.createBorrowTransaction(borrowTransaction); }
	 */
}
