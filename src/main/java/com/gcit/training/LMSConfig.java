package com.gcit.training;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.BookCopiesDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.BookLoansDAO;
import com.gcit.training.lms.dao.BorrowerDAO;
import com.gcit.training.lms.dao.GenreDAO;
import com.gcit.training.lms.dao.LibraryBranchDAO;
import com.gcit.training.lms.dao.PublisherDAO;
import com.gcit.training.lms.service.AdministrativeService;

@EnableTransactionManagement

@Configuration
public class LMSConfig {
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/library";
	private static String username = "root";
	private static String password = "";
	
	@Bean
	public BasicDataSource datasource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager txManager(){
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(datasource());
		return tx;
	}
	
	@Bean
	public JdbcTemplate template(){
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(datasource());
		
		return template;
	}
	
	@Bean
	public AuthorDAO aDAO(){
		AuthorDAO adao = new AuthorDAO();
		
		return adao;
		
	}
	
	
	@Bean
	public AdministrativeService adminService(){
		AdministrativeService adminService = new AdministrativeService();
		return adminService;
	}
	
	//add all DAOS
//	@Bean
//	public AuthorDAO aDAO(){
//		AuthorDAO adao = new AuthorDAO();
//		
//		return adao;
//		
//	}
//	@Bean
//	public AuthorDAO aDAO(){
//		AuthorDAO adao = new AuthorDAO();
//		
//		return adao;
//		
//	}
//	@Bean
//	public AuthorDAO aDAO(){
//		AuthorDAO adao = new AuthorDAO();
//		
//		return adao;
//		
//	}
	@Bean
	public PublisherDAO pDAO(){
		PublisherDAO pdao = new PublisherDAO();
		
		return pdao;
		
	}
	@Bean
	public BookDAO bDAO(){
		BookDAO bdao = new BookDAO();
		
		return bdao;
	}
	@Bean
	public BorrowerDAO boDAO(){
		BorrowerDAO boDAO = new BorrowerDAO();
		return boDAO;		
	}
	@Bean
	public LibraryBranchDAO lbDAO(){
		LibraryBranchDAO lbDAO = new LibraryBranchDAO();
		return lbDAO;
	}
	@Bean
	public GenreDAO geDAO(){
		GenreDAO geDAO = new GenreDAO();
		return geDAO;
	}
	@Bean
	public BookCopiesDAO bcDAO(){
		BookCopiesDAO bcDAO = new BookCopiesDAO();
		return bcDAO;
	}
	@Bean
	public BookLoansDAO blDAO(){
		BookLoansDAO blDAO = new BookLoansDAO();
		return blDAO;
		
	}
	
	
	
	

}
