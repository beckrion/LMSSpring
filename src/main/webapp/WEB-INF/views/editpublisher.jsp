<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>

<%
	Integer publisheId = Integer.parseInt(request.getParameter("publisherId"));
	
%> 
<div class="modal-content">
	<form action="updatePublisher?publisherId=<%=publisheId%>" method="post">
	${result}
		<h2>Enter Publisher details below:</h2>

		Publisher Name: <input type="text" name="publisherName"> 
		
		<br>
		
		Publisher address:<input type = "text" name="publisherAdd">
		
		<button type="submit" class="btn btn-sm btn-primary">Editor Publisher</button>
	</form>
</div>