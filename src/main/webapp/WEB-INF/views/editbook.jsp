<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>

<%
	Integer authorId = Integer.parseInt(request.getParameter("bookId"));
	
%> 
<div class="modal-content">
	<form action="updateBook?bookId=<%=authorId%>" method="post">
	${result}
		<h2>Enter Book details below:</h2>

		Book Name: <input type="text" name="bookName"> 
		<button type="submit" class="btn btn-sm btn-primary">Editor Book</button>
	</form>
</div>