package com.gcit.training.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.entity.Author;


public class AdministrativeService {
	
	@Autowired
	AuthorDAO adao;
	
	@Transactional
	public void addAuthor(Author author) throws SQLException{
		adao.create(author);
	}
	
	public List<Author> getAllAuthors(int pageNo, int pageSize, String searchString) throws SQLException{
			if(StringUtils.hasLength(searchString)){
				return adao.readAll(pageNo, pageSize);
			}else{
				return adao.readByName(searchString, pageNo);
			}
	}

	public void deleteAuthor(Author author) throws SQLException {
			adao.delete(author);
	}

	public List<Author> searchAuthors(String searchString, Integer pageNo) throws SQLException {
			return adao.readByName(searchString, pageNo);
	}

}
