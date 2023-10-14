package business;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

final public class checkoutRecord implements Serializable{

	private BookCopy bookCopy;
	private LibraryMember member;
	private LocalDateTime dueDate;
	private LocalDateTime checkoutDate;
	public checkoutRecord(BookCopy copy, LibraryMember member, LocalDateTime dueDate, LocalDateTime checkoutDate){
		this.bookCopy = copy;
		this.member = member;
		this.dueDate = dueDate;
		this.checkoutDate = checkoutDate;
	}
	public BookCopy getBookCopy() {
		return bookCopy;
	}
	public LibraryMember getMember() {
		return member;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public LocalDateTime getCheckoutDate() {
		return checkoutDate;
	}
	private static final long serialVersionUID = 2226197306790714013L;
	
}
