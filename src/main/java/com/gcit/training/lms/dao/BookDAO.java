package com.gcit.training.lms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.gcit.training.lms.entity.Book;

public class BookDAO {
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	AuthorDAO adao;

	public void addBook(Book book){
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		template.update("insert into tbl_book (title, pubId) values (?, ?)",
				new Object[] { book.getTitle(), book.getPublisher().getPublisherId()}, keyHolder);
		int bookId = keyHolder.getKey().intValue();
		
//		for(Author a: book.getAuthors()){
//			
//		}
		
		//TODO Genre
	}
}
