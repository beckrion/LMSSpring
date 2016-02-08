<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="java.util.List"%>

<%
	Integer BookId = Integer.parseInt(request.getParameter("bookId"));
	Integer branchId = null;	
	branchId = Integer.parseInt(request.getParameter("branchId"));
%> 

<div class="modal-content">
	<form action="updateBorrInfo?BookId=<%=BookId%>&BranchId=<%=branchId%>" method="post">
	${result}
		<h2>Enter Borrower details below:</h2>

		Borrower Card No: <input type="text" name="borrowerNo">
		<br>
		Out Date Format:YYYY-MM-DD:<br>
		<input type="text" name="dateOut" >  
		<br>
		Due Date Format:YYYY-MM-DD:<br>
		<input type="text" name="dateDue">
		<br>
		
		<button type="submit" class="btn btn-sm btn-primary">Submit Information</button>
	</form>
</div>

