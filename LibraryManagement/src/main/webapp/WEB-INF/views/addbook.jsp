<html>
<body>
<h2>Add New Book</h2>
<form action="save" method="post">
    Book ID: <input type="text" name="bookId"/><br><br>
    Title: <input type="text" name="title"/><br><br>
    Author: <input type="text" name="author"/><br><br>
    Category: <input type="text" name="category"/><br><br>
    Price: <input type="text" name="price"/><br><br>
    <input type="submit" value="Add Book"/>
</form>
<br><a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>