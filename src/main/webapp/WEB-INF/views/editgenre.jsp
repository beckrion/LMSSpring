<%
	Integer publisheId = Integer.parseInt(request.getParameter("genreId"));
	
%> 
<div class="modal-content">
	<form action="updateGenre?genreId=<%=publisheId%>" method="post">
	${result}
		<h2>Enter Genre details below:</h2>

		Publisher Name: <input type="text" name="genreName"> 
		
		<br>
		
		
		<button type="submit" class="btn btn-sm btn-primary">Editor Genre</button>
	</form>
</div>>