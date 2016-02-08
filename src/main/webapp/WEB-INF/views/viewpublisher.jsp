<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%-- 	<jsp:include /> --%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Publisher"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//AdministrativeService adminService = new AdministrativeService();
List<Publisher> allBooks = null;
allBooks =(List<Publisher>)request.getAttribute("books");
%>
 <div class="row">
	<div class="col-md-6">
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>Publisher Name</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Publisher book : allBooks) {
				%>
				<tr>
					<td><%=book.getPublisherId()%></td>
					<td><%=book.getPublisherName()%></td>
					<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="editPublisher?publisherId=<%=book.getPublisherId()%>">Edit Author</button></td>
					<td><button type="button" class="btn btn-sm btn-danger"
							onclick="javascript:location.href='deletePublisher?publisherId=<%=book.getPublisherId()%>';">Delete
							book</button>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</div>
<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>