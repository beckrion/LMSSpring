package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookLoans;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.LibraryBranch;


public class BookLoansDAO extends AbstractDAO implements ResultSetExtractor<List<BookLoans>>{
	@Autowired
	JdbcTemplate template;
	

	
	@Autowired
	LibraryBranchDAO lbRun;
	
	@Autowired
	BookDAO bkRun;
	
	@Autowired
	BorrowerDAO borrRun;
	
	
	
	public void create(BookLoans a) throws SQLException {
		template.update("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate,dateIn) values (?,?,?,?,?,?)",
				new Object[] { a.getBook().getBookId(),a.getBranch().getBranchId(),a.getDateOut(),a.getDueDate(),a.getDateIn() });
	}
	public void borrow(BookLoans a) throws SQLException {
		template.update("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,?,?)",
				new Object[] { a.getBook().getBookId(),a.getBranch().getBranchId(),a.getBorrower().getCardNo(),a.getDateOut(),a.getDueDate() });
	}
	public void updateDateOut(BookLoans a) throws SQLException {
		template.update("update tbl_book_loans set dateOut = ? where bookId =? and branchId = ? and cardNo = ?",
				new Object[] { a.getDateOut(),a.getBook().getBookId(),a.getBranch().getBranchId(),a.getBorrower().getCardNo() });
	}
	public void updateDueDate(BookLoans a) throws SQLException {
		template.update("update tbl_book_loans set dueDate = ? where bookId =? and branchId = ? and cardNo = ?",
				new Object[] { a.getDueDate(),a.getBook().getBookId(),a.getBranch().getBranchId(),a.getBorrower().getCardNo() });
	}
	public void updateDateIn(BookLoans a) throws SQLException {
		template.update("update tbl_book_loans set dateIn = ? where bookId =? and cardNo = ?",
				new Object[] { a.getDateIn(),a.getBook().getBookId(),a.getBorrower().getCardNo() });
	}
//	@Override
//	protected List<BookLoans> processResult(ResultSet rs) throws SQLException {
//		// TODO Auto-generated method stub
//		List<BookLoans> aList = new ArrayList<BookLoans>();
//		while(rs.next())
//		{
//			BookLoans a = new BookLoans();
//			Book bk = bkRun.readOne(rs.getInt("bookId"));
//			LibraryBranch lb = lbRun.readOne(rs.getInt("branchId"));
//			BorrowerDAO borrRun = new BorrowerDAO();
//			Borrower borr = borrRun.readOne(rs.getInt("cardNo"));
//			a.setBook(bk);
//			a.setBranch(lb);
//			a.setBorrower(borr);
//			a.setDateOut(rs.getString("dateOut"));
//			a.setDueDate(rs.getString("dueDate"));
//			a.setDateIn(rs.getString("dateIn"));
//			aList.add(a);
//		}
//		return aList;
//	}
	public List<BookLoans> readAll() throws SQLException {
		return (List<BookLoans>) template.query("select * from tbl_book_loans", this);
	}
	public List<BookLoans> readByCardNo(BookLoans a) throws SQLException {
		System.out.println(a.getBorrower().getCardNo());
		return (List<BookLoans>) template.query("select * from tbl_book_loans where cardNo=? ",new Object[] { a.getBorrower().getCardNo()}, this);
	}
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<BookLoans> aList = new ArrayList<BookLoans>();
		while(rs.next())
		{
			BookLoans a = new BookLoans();
			Book bk = bkRun.readOne(rs.getInt("bookId"));
			LibraryBranch lb = lbRun.readOne(rs.getInt("branchId"));
			Borrower borr = borrRun.readOne(rs.getInt("cardNo"));
			a.setBook(bk);
			a.setBranch(lb);
			a.setBorrower(borr);
			a.setDateOut(rs.getString("dateOut"));
			a.setDueDate(rs.getString("dueDate"));
			a.setDateIn(rs.getString("dateIn"));
			aList.add(a);
		}
		return aList;
	}

}
