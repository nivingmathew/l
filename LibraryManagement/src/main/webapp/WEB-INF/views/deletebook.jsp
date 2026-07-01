<html>
<body>
<h2>Delete Book</h2>
<form action="processDelete" method="post">
    Book ID: <input type="text" name="bookId"/>
    <input type="submit" value="Delete Book"/>
</form>
<br><a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>