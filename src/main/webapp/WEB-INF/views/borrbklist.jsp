<%@include file="include.html"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.training.lms.entity.Book"%>

<%
List<Book> book = null;
book =(List<Book>) request.getAttribute("bookList");
String BranchId = (String)request.getAttribute("branchId");
Integer branch = Integer.parseInt(BranchId);
%>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="authorsTable">
			<thead>
				<tr>
					<th>#</th>
					<th>Book Name</th>
					<th>Borrow</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Book books : book) {
			
				%>
				<tr>
					<td><%=books.getBookId()%></td>
					<td><%=books.getTitle()%></td>
					<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="borrow?bookId=<%=books.getBookId()%>&branchId=<%=branch%>">Borrow This</button></td>
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