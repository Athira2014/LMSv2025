package com.athira.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "borrowtransactions")
public class BorrowTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BTransId", nullable = false)
	private Integer bTransId;

	@ManyToOne
	@JoinColumn(name = "BookId", insertable = false, updatable = false) // BookId will be the foreign key
	private Book book;

	@Column(name = "BookId", nullable = false)
	private Integer bookId;

	@ManyToOne
	@JoinColumn(name = "MemberId", insertable = false, updatable = false)
	private Members members;

	@Column(name = "MemberId", nullable = false)
	private Integer memberId;

	@Column(name = "BorrowDate", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime borrowDate;

	@Column(name = "ReturnDate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime returnDate;

	@Column(name = "ReturnStatus", length = 20)
	private String returnStatus;

	public enum ReturnStatus {
		RETURNED, NOT_RETURNED;
		
		public static boolean isValid(String status) {
			try {
				ReturnStatus.valueOf(status.toUpperCase());
				return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
	}

	public BorrowTransaction() {
	}

	public BorrowTransaction(Integer bTransId, Book book, Integer bookId, Members members, Integer memberId,
			DateTime borrowDate, DateTime returnDate, String returnStatus) {
		super();
		this.bTransId = bTransId;
		this.book = book;
		this.bookId = bookId;
		this.members = members;
		this.memberId = memberId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.returnStatus = returnStatus;
	}

	public Integer getbTransId() {
		return bTransId;
	}

	public void setbTransId(Integer bTransId) {
		this.bTransId = bTransId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getBookId() {
		if (bookId == null || bookId <= 0) {
			throw new IllegalArgumentException("Book field should not be null.");
		}
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		if (memberId == null || memberId <= 0) {
			throw new IllegalArgumentException("Member field should not be null.");
		}
		this.memberId = memberId;
	}

	public DateTime getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(DateTime borrowDate) {

		if (borrowDate == null) {
			throw new IllegalArgumentException("Borrow date cannot be null.");
		}
		if (borrowDate.isAfter(new DateTime())) {
			throw new IllegalArgumentException("Borrow date cannot be in the future.");
		}
		this.borrowDate = borrowDate;
	}

	public DateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(DateTime returnDate) {

		if (returnDate != null && returnDate.isBefore(borrowDate)) {
			throw new IllegalArgumentException("Return date cannot be before borrow date.");
		}
		this.returnDate = returnDate;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		if (returnStatus != null && !returnStatus.isEmpty()) {
			if (!ReturnStatus.isValid(returnStatus)) {
				throw new IllegalArgumentException(
						"Invalid return status. Allowed values are: Returned, Not Returned.");
			}
		}
		this.returnStatus = returnStatus;
	}

}
