package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;

public class BookDAO implements ResultSetExtractor<List<Book>> {
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	AuthorDAO adao;
	
	@Autowired
	PublisherDAO pdao;

	public void addBook(Book book){
		final String title = book.getTitle();
		final int pubId = book.getPublisher().getPublisherId();
		//final int pubId = book.getPublisher().getPublisherId();
		final String INSERT_SQL = "insert into tbl_book (title, pubId) values (?, ?) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, title);
				ps.setInt(2, pubId);
				return ps;
			}
		}, keyHolder);
		int bookId = keyHolder.getKey().intValue();
		for(Author a: book.getAuthors()){
			template.update("insert into tbl_book_authors (bookId, authorId) values (?, ?)",new Object[] {
					bookId,a.getAuthorId() });
		}
		for(Genre a: book.getGenre()){
			template.update("insert into tbl_book_genres (bookId, genre_id) values (?, ?)",new Object[] {
					bookId,a.getGenreId() });
		}
	}
	
	public void update(Book a) throws SQLException {
		template.update("update tbl_book set title = ?,pubId = ? where bookId = ?",
				new Object[] { a.getTitle(), a.getPublisher().getPublisherId(),
						a.getBookId() });
	}
	public void delete(Book a) throws SQLException {
		template.update("delete from tbl_book_authors where bookId = ?",new Object[] { a.getBookId() });
		template.update("delete from tbl_book_genres where bookId = ?",new Object[] { a.getBookId() });
		template.update("delete from tbl_book where bookId = ?",
				new Object[] { a.getBookId() });

	}
	public void updateTitle(Book a) throws SQLException {
		template.update("update tbl_book set title = ? where bookId = ?",
				new Object[] { a.getTitle(), a.getBookId() });

	}
	public void updatePubId(Book a) throws SQLException {
		template.update("update tbl_book set pubId = ? where bookId = ?", new Object[] {
				a.getPublisher().getPublisherId(), a.getBookId() });

	}
	public List<Book> readAll() throws SQLException {
		return (List<Book>) template.query("select * from tbl_book", this);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Book> aList = new ArrayList<Book>();
		while (rs.next()) {
			Book a = new Book();
			a.setBookId(rs.getInt("BookId"));
			a.setTitle(rs.getString("Title"));
			Publisher p = pdao.readOne(rs.getInt("pubId"));
			a.setPublisher(p);
			aList.add(a);
		}
		return aList;
	}


	public Book readOne(int bookId) throws SQLException {
		List<Book> bkList = (List<Book>) template.query(
				"select * from tbl_book where bookId = ?",
				new Object[] { bookId },this);

		if (bkList != null && bkList.size() > 0) {
			return bkList.get(0);
		} else {
			return null;
		}
	}
	
}
