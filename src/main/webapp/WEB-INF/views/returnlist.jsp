<%@include file="include.html"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.training.lms.entity.Book"%>
<%@ page import="com.gcit.training.lms.entity.LibraryBranch"%>
<%
List<Book> book = null;
book =(List<Book>) request.getAttribute("bookList");
int card = (Integer)request.getAttribute("cardNo");
List<LibraryBranch> branchId = null;
branchId = (List<LibraryBranch>)request.getAttribute("branchId");
out.print("+++++++++"+branchId.size());
//request.setAttribute("cardNo", card);
//int cardNo = Integer.parseInt(card);
%>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="authorsTable">
			<thead>
				<tr>
					<th>#</th>
					<th>Book Name</th>
					<th>Return</th>
				</tr>
			</thead>
			<tbody>
				<%
				int count = 0;
					for (Book books : book) {
			
				%>
				<tr>
					<td><%=books.getBookId()%></td>
					<td><%=books.getTitle()%></td>

					<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="return?bookId=<%=books.getBookId()%>&bid=<%=branchId.get(count).getBranchId()%>&cardNo=<%=card%>">Return This</button></td>
					<%count++; %>
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