<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exemplo JSP</title>
</head>
<body>
    <h1>Olá, <%= request.getParameter("nome") %>!</h1>
</body>
</html>
