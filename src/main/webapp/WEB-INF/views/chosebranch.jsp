<%@include file="include.html"%>
<%@ page import="com.gcit.training.lms.entity.LibraryBranch"%>
<%@ page import="com.gcit.training.lms.dao.LibraryBranchDAO"%>
<%@ page import="java.util.List"%>

<form action="borrowBook" method="post">
<select name="libB" size ="3">
<%
List<LibraryBranch> lb = null;
lb = (List<LibraryBranch>)request.getAttribute("libInfo");

		for(LibraryBranch libBran : lb)
{	
	out.print("<option value="+libBran.getBranchId()+" /> "+libBran.getBranchName()+"</option>");
	
} 

%>
</select>

<button type="submit" class="btn btn-sm btn-danger">Enter</button>
</form>
