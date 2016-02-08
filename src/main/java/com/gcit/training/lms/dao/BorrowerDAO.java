package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Borrower;



public class BorrowerDAO extends AbstractDAO implements ResultSetExtractor<List<Borrower>> {
	@Autowired
	JdbcTemplate template;
	public void create(Borrower a) throws SQLException {
		template.update("insert into tbl_borrower (name,address,phone) values (?,?,?)",
				new Object[] { a.getName(),a.getAddress(),a.getPhone() });
	}
	public void updateName(Borrower a) throws SQLException {
		template.update("update tbl_borrower set name = ? where cardNo =?",
				new Object[] { a.getName(),a.getCardNo() });
	}
	public void updateAddress(Borrower a) throws SQLException {
		template.update("update tbl_borrower set address = ? where cardNo =?",
				new Object[] { a.getAddress(),a.getCardNo() });
	}
	public void updatePhone(Borrower a) throws SQLException {
		template.update("update tbl_borrower set phone = ? where cardNo =?",
				new Object[] { a.getPhone(),a.getCardNo() });
	}
	public void delete(Borrower a) throws SQLException {
		template.update("delete from tbl_borrower where cardNo = ? ",
				new Object[] { a.getCardNo() });
	}
//	@Override
//	protected List<Borrower> processResult(ResultSet rs) throws SQLException {
//		// TODO Auto-generated method stub
//		List<Borrower> aList = new ArrayList<Borrower>();
//		while(rs.next()){
//			Borrower a =new Borrower();
//			a.setCardNo(rs.getInt("cardNo"));
//			a.setName(rs.getString("name"));
//			a.setAddress(rs.getString("address"));
//			a.setPhone(rs.getString("phone"));
//			aList.add(a);
//		}
//		return aList;
//	}
	public List<Borrower> readAll() throws SQLException {
		return (List<Borrower>) template.query("select * from tbl_borrower", this);
	}
	public Borrower readOne(int cardNo) throws SQLException {
		List<Borrower> bkList = (List<Borrower>) template.query(
				"select * from tbl_borrower where cardNo = ?",
				new Object[] { cardNo },this);
		
		if(bkList != null && bkList.size() > 0) {
			return bkList.get(0);
		} else {
			return null;
		}
	}
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower a =new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			aList.add(a);
		}
		return aList;
	}

}
