package com.gcit.training.lms.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
//@Entity
//@Table(name = "tbl_author")
//@XmlRootElement(name="root")

//tbl_author table
public class Author {
	
	private int authorId;
	private String authorName;
	private List<Book> books;
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
