package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Publisher;

public class PublisherDAO implements ResultSetExtractor<List<Publisher>>  {
	@Autowired
	JdbcTemplate template;
	


	public void create(Publisher p) throws SQLException {
		template.update("insert into tbl_publisher (publisherName, publisherAddress) values (?,?)",
				new Object[] { p.getPublisherName(), p.getPublisherAddress() });
	}

	public void update(Publisher p) throws SQLException {
		template.update("update tbl_publisher set publisherName = ?, publisherAddress = ? where publisherId = ?",
				new Object[] { p.getPublisherName(), p.getPublisherAddress(),
						p.getPublisherId() });
	}

	public void delete(Publisher p) throws SQLException {
		template.update("delete from tbl_publisher where publisherId = ?",
				new Object[] { p.getPublisherId() });
	}

	public Publisher readOne(int publisherId) throws SQLException {
		List<Publisher> pubList = (List<Publisher>) template.query(
				"select * from tbl_publisher where publisherId = ?",
				new Object[] { publisherId },this);
		
		if(pubList != null && pubList.size() > 0) {
			return pubList.get(0);
		} else {
			return null;
		}
	}

	public List<Publisher> readAll() throws SQLException {
		return (List<Publisher>) template.query("select * from tbl_publisher", this);
	}

	public List<Publisher> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Publisher>) template.query(
				"select * from tbl_publisher where publisherName like ?",
				new Object[] { qString },this);
	}

//	@Override
//	protected List<Publisher> processResult(ResultSet rs) throws SQLException {
//
//	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Publisher> aList = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));

			aList.add(a);
		}

		return aList;
		// TODO Auto-generated method stub
	}

}
