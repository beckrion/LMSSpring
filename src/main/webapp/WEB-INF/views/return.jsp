<%
	Integer BookId = Integer.parseInt(request.getParameter("bookId"));
	Integer cardNo = null;	
	cardNo = Integer.parseInt(request.getParameter("cardNo"));
	int branchId = Integer.parseInt(request.getParameter("bid"));
	
%> 
<form action="returnSubmit?bookId=<%=BookId %>&cardNo=<%=cardNo%>&branch=<%=branchId%>" method="post">
Please Enter Return Date<br>
<input type="text" name="datein" >
<button type="submit" class="btn btn-sm btn-primary">Submit</button>
</form>