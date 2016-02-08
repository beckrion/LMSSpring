<%@include file="include.html"%>
	<form action=addPublisher method="post">
	${result}
		<h2>Enter publisher details below:</h2>

		Publisher Name: <input type="text" name="pubName"> 
		<br>
		Publisher Address:<input type="text" name="pubAddr">
		<button type="submit" class="btn btn-sm btn-primary">Add Author</button>
	</form>
