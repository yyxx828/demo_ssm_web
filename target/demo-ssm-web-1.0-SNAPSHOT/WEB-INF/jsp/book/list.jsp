<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书籍列表</title>
</head>
<body>
    <br>
    <br>
    <br>
    <table border="1" align="center">
        <thead>
            <tr>
                <th>编号</th>
                <th>书名</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${list}">
                <tr>
                    <td>${e.bookId}</td>
                    <td>${e.name}</td>
                    <td>${e.number}</td>
                    <td><a href="/book/${e.bookId}/detail">详细</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
