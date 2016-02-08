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
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.BookLoans;
import com.gcit.training.lms.entity.LibraryBranch;

public class BookCopiesDAO extends AbstractDAO implements ResultSetExtractor<List<BookCopies>>{
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	LibraryBranchDAO brhRun;
	
	@Autowired
	BookDAO bkRun;
	
	
	public void create (BookCopies a) throws SQLException
	{
		template.update("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)",new Object[] { a.getBook().getBookId(),a.getBranch().getBranchId(),a.getNoOfCopies() });
	}
	public void updateBook(BookCopies newBk,BookCopies oldBk) throws SQLException {
		template.update("update tbl_book_copies set bookId = ? where bookId =? and branchId = ?",
				new Object[] { newBk.getBook().getBookId(),oldBk.getBook().getBookId(),newBk.getBranch().getBranchId() });
	}
	public void updateBranch(BookCopies newBk,BookCopies oldBk) throws SQLException {
		template.update("update tbl_book_copies set branchId = ? where bookId =? and branchId = ?",
				new Object[] { newBk.getBranch().getBranchId(),oldBk.getBook().getBookId(),newBk.getBranch().getBranchId() });
	}
	public void updateNo(BookCopies a) throws SQLException {
		template.update("update tbl_book_copies set noOfCopies = ? where branchId = ? and bookId = ?",
				new Object[] { a.getNoOfCopies(),a.getBranch().getBranchId(),a.getBook().getBookId() });
	}
	public void delete(BookCopies a) throws SQLException {
		template.update("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { a.getBook().getBookId(),a.getBranch().getBranchId() });
	}
//	@Override
//	protected List<BookCopies> processResult(ResultSet rs) throws SQLException {
//		// TODO Auto-generated method stub
//		List<BookCopies> aList = new ArrayList<BookCopies>();
//		while(rs.next())
//		{
//			BookCopies a = new BookCopies();
//			Book bk = bkRun.readOne(rs.getInt("bookId"));
//			LibraryBranch brh = brhRun.readOne(rs.getInt("branchId"));
//			a.setBook(bk);
//			a.setBranch(brh);
//			a.setNoOfCopies(rs.getInt("noOfCopies"));
//			aList.add(a);
//		}
//		return aList;
//	}
	public List<BookCopies> readAll() throws SQLException {
		return (List<BookCopies>) template.query("select * from tbl_book_copies", this);
	}
	
	public List<BookCopies> readOneBrach(LibraryBranch lb) throws SQLException {
		return (List<BookCopies>) template.query("select * from tbl_book_copies where branchId = ?",new Object[] {lb.getBranchId()}, this);
	}
	public Integer readNumOfCop(BookCopies a)
	{
		return template.queryForObject("select noOfCopies from tbl_book_copies where branchId=? and bookId = ?",new Object[] {a.getBranch().getBranchId(),a.getBook().getBookId()},Integer.class);
		
	}
	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<BookCopies> aList = new ArrayList<BookCopies>();
		while(rs.next())
		{
			BookCopies a = new BookCopies();
			Book bk = bkRun.readOne(rs.getInt("bookId"));
			LibraryBranch brh = brhRun.readOne(rs.getInt("branchId"));
			a.setBook(bk);
			a.setBranch(brh);
			a.setNoOfCopies(rs.getInt("noOfCopies"));
			aList.add(a);
		}
		return aList;
	}
	

}
