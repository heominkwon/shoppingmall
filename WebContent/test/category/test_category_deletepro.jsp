<%@page import="category.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>category delete pro</title>
</head>
<body>
	<%
		CategoryDAO categoryManger = CategoryDAO.getInstance();
		categoryManger.deleteCategory(request.getParameter("C_NO"));
		response.sendRedirect("test_category_main.jsp");
	%>
</body>
</html>