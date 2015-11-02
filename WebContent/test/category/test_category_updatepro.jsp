<%@page import="category.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>update category pro</title>
</head>
<body>
	<%
		CategoryDAO categoryManager = CategoryDAO.getInstance();
		categoryManager.updateC_name(
				request.getParameter("C_NO"),
				request.getParameter("C_NAME"));
		
		response.sendRedirect("test_category_main.jsp");
	%>
</body>
</html>