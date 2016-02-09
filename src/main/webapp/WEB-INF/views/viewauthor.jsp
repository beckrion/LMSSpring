<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.service.AdministrativeService"%>
<%@ page import="com.gcit.training.lms.service.ConnectionUtil"%>
<%@ page import="com.gcit.training.lms.entity.Author"%>
<%@ page import="com.gcit.training.lms.dao.AuthorDAO"%>
<%@ page import="com.gcit.training.HomeController"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//AdministrativeService adminService = new AdministrativeService();
	List<Author> authors = null;
	if(request.getAttribute("authors")!=null){
		authors = (List<Author>)request.getAttribute("authors");
	}else{
		//authors = adminService.getAllAuthors(1, 1, "");
	}
	String searchString = "";
	if(request.getParameter("searchString")!=null)
		searchString = request.getParameter("searchString");
	Integer NumOfPage = (Integer)request.getAttribute("pageNu");
	
%>
<script>
function searchAuthor(pageNo){
	//document.forms["authorsForm"].action = "/LMSWeb/searchAuthor?pageNo="+pageNo;
	//document.forms["authorsForm"].submit();
	
	$.ajax({
		  url: "searchAuthor",
		  data: {
			  searchString: $('#searchString').val(),
			  pageNo: pageNo
		  }
		}).done(function(data) {
			$('#authorsTable').html(data);
		});
}
</script>

<div class="page-header">
	<h1>List of Authors in LMS Application</h1>
	${result }
</div>
<form action=searchAuthor method="post">
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1">Search</span>
  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" name="searchString" id="searchString" value=<%=searchString%>>
</div>
<button type="submit" class="btn btn-sm btn-primary" >Search!</button>
</form>
<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <%for(int i = 1;i<=NumOfPage;i++)
    {
    	out.print("<li><a href=\"viewAuthorPage?page="+i+"\">"+i+"</a></li>");
    }
    %>
<!--     <li><a href="javascript:searchAuthor(1)">1</a></li>
    <li><a href="javascript:searchAuthor(2)">2</a></li>
    <li><a href="javascript:searchAuthor(3)">3</a></li>
    <li><a href="javascript:searchAuthor(4)">4</a></li>
    <li><a href="javascript:searchAuthor(5)">5</a></li> -->
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="authorsTable">
			<thead>
				<tr>
					<th>#</th>
					<th>Author Name</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Author author : authors) {
			
				%>
				<tr>
					<td><%=author.getAuthorId()%></td>
					<td><%=author.getAuthorName()%></td>
					<td align="center"><button type="button" class="btn btn btn-primary" data-toggle="modal" data-target="#myModal1" href="editauthor?authorId=<%=author.getAuthorId()%>">Edit Author</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" onclick="javascript:location.href='deleteAuthor?authorId=<%=author.getAuthorId()%>';">Delete Author</button>
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