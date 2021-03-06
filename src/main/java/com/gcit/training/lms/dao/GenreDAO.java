package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;

public class GenreDAO extends AbstractDAO implements ResultSetExtractor<List<Genre>> {
	@Autowired
	JdbcTemplate template;
	
	public void create (Genre a) throws SQLException
	{
		template.update("insert into tbl_genre (genre_id,genre_name) values (?,?)",new Object[] { a.getGenreId(),a.getGenreName() });
	}
	public void update (Genre a) throws SQLException
	{
		template.update("update tbl_genre set genre_name = ? where genre_id = ?",new Object[]{a.getGenreName(),a.getGenreId()});
	}
	public void delete(Genre a) throws SQLException {
		template.update("delete from tbl_genre where genre_id = ?",new Object[]{a.getGenreId()});
	}
//	@Override
//	protected List<Genre> processResult(ResultSet rs) throws SQLException {
//		List<Genre> aList = new ArrayList<Genre>();
//		while(rs.next()){
//			  Genre a = new Genre();
//			a.setGenreId(rs.getInt("genre_id"));
//			a.setGenreName(rs.getString("genre_name"));
//			aList.add(a);
//		}
//		return aList;
//	}
	public List<Genre> readAll() throws SQLException {
		return (List<Genre>) template.query("select * from tbl_genre", this);
	}
	public Genre readOne(int genreId) throws SQLException {
		List<Genre> list = (List<Genre>) template.query(
				"select * from tbl_genre where genre_id = ?",
				new Object[] { genreId },this);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Genre> aList = new ArrayList<Genre>();
		while(rs.next()){
			  Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			aList.add(a);
		}
		return aList;
	}

}
