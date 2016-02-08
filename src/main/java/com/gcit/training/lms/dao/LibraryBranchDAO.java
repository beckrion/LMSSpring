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
import com.gcit.training.lms.entity.LibraryBranch;

public class LibraryBranchDAO extends AbstractDAO implements ResultSetExtractor<List<LibraryBranch>> {
	
	@Autowired
	JdbcTemplate template;
	
	public void create (LibraryBranch a) throws SQLException
	{
		template.update("insert into tbl_library_branch (branchId,branchName,branchAddress) values (?,?,?)",new Object[] { a.getBranchId(),a.getBranchName(),a.getBranchAddress() });
	}
	public void updateName(LibraryBranch a) throws SQLException {
		template.update("update tbl_library_branch set branchName = ? where branchId = ?",
				new Object[] { a.getBranchName(),a.getBranchId() });
	}
	public void updateAddress(LibraryBranch a) throws SQLException {
		template.update("update tbl_library_branch set branchAddress = ? where branchId = ?",
				new Object[] { a.getBranchName(),a.getBranchId() });
	}
	public void delete(LibraryBranch a) throws SQLException {
		template.update("delete from tbl_library_branch where branchId = ?",
				new Object[] { a.getBranchId() });
	}
//	@Override
//	protected List<LibraryBranch> processResult(ResultSet rs) throws SQLException {
//		// TODO Auto-generated method stub
//		List<LibraryBranch> aList = new ArrayList<LibraryBranch>();
//		while(rs.next())
//		{
//			LibraryBranch a = new LibraryBranch();
//			a.setBranchId(rs.getInt("branchId"));
//			a.setBranchName(rs.getString("branchName"));
//			a.setBranchAddress(rs.getString("branchAddress"));
//			aList.add(a);
//		}
//		return aList;
//	}
	public LibraryBranch readOne(int branchId) throws SQLException {
		List<LibraryBranch> list = (List<LibraryBranch>) template.query(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId },this);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	public List<LibraryBranch> readAll() throws SQLException {
		return (List<LibraryBranch>) template.query("select * from tbl_library_branch", this);
	}
	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<LibraryBranch> aList = new ArrayList<LibraryBranch>();
		while(rs.next())
		{
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			aList.add(a);
		}
		return aList;
	}

}
