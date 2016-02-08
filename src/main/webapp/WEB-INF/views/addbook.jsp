<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.entity.Publisher"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.entity.Genre"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="java.util.List"%>
	<form action="addBook" method="post">
	<%
	//AdministrativeService adminService = new AdministrativeService();
	
	List<Publisher> pub = null;
	List<Author> au = null;
	List<Genre> ge = null;
	
	pub = (List<Publisher>)request.getAttribute("pub");
	au = (List<Author>)request.getAttribute("author");
	ge = (List<Genre>)request.getAttribute("genre");
	//allBooks =(List<Book>)request.getAttribute("books");

	%>>
	${result}
		<h2>Enter Book details below:</h2>

		Book Name: <input type="text" name="bookName"> 
		<br />
		Select Publish
		<br />
		<select name="pubC" size ="3">
		
		<%		for(Publisher publish : pub)
	{	
		out.print("<option value="+publish.getPublisherId()+" /> "+publish.getPublisherName()+"</option>");
		
	} %>
	</select>
	<br />
		Select Genre
	<br />
			<select name="genreC" size ="3" multiple="multiple">
		
		<%		for(Genre genre : ge)
	{	
		out.print("<option value="+genre.getGenreId()+" /> "+genre.getGenreName()+"</option>");
		
	} %>
	</select>
	
		<br />
		Select Author
	<br />
			<select name="auth" size ="3" multiple="multiple">
		
		<%		for(Author author : au)
	{	
		out.print("<option value="+author.getAuthorId()+" /> "+author.getAuthorName()+"</option>");
		
	} %>
	</select>
	

		<br />
		<button type="submit" class="btn btn-sm btn-primary" >Add Book</button>

	</form>