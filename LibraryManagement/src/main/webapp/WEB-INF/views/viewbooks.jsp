<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Book Roster</h2>
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Category</th>
        <th>Price</th>
    </tr>
    <c:forEach var="b" items="${books}">
        <tr>
            <td>${b.bookId}</td>
            <td>${b.title}</td>
            <td>${b.author}</td>
            <td>${b.category}</td>
            <td>${b.price}</td>
        </tr>
    </c:forEach>
</table>
<br><a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>