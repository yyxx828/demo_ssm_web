<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书籍详细</title>
</head>
<body>
    <br>
    <br>
    <br>
    <table border="1" align="center">
        <tbody>
            <tr>
                <td>编号</td>
                <td>${entity.bookId}</td>
            </tr>
            <tr>
                <td>书名</td>
                <td>${entity.name}</td>
            </tr>
            <tr>
                <td>数量</td>
                <td>${entity.number}</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
