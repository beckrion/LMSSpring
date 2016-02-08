package com.gcit.training.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
		try{
			//AuthorDAO badao = new AuthorDAO(connection);
			if(StringUtils.isEmpty(searchString)){
				//System.out.println((adao.readAll(1, 1)).size());
				List<Author> a = adao.readAll(1, 1);
				System.out.println("size :" + a.size());
				return a;
			}else{
				return adao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void deleteAuthor(Author author) throws SQLException {
			adao.delete(author);
	}

	public List<Author> searchAuthors(String searchString, Integer pageNo) throws SQLException {
			return adao.readByName(searchString, pageNo);
	}

}
