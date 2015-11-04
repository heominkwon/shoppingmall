<%@page import="category.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>insert category pro</title>
</head>
<body>
	<%
		CategoryDAO categoryManager = CategoryDAO.getInstance();
		categoryManager.insertCategory(request.getParameter("c_name"));
		response.sendRedirect("test_category_main.jsp");
	%>
</body>
</html>