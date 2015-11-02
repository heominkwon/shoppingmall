<%@page import="category.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="category.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>TEST CATEGORY MAIN</title>
</head>
<body>
	<%
		CategoryDAO 	  categoryManager    = CategoryDAO.getInstance();
		List<CategoryDTO> categoryList	     = categoryManager.selectCategoryAll();
		int 			  maxCategoryIndex   = categoryManager.getCategoryCount();
	%>
	<a href="../test_main.jsp">HOME</a>
	<p />
	<a href="test_category_insertform.jsp">insert</a>
	<p />
	<a href="test_category_deleteform.jsp">delete</a>
	<p />
	<a href="test_category_updateform.jsp">update</a>
	<p />

	<table border="1">
		<tr>
			<td colspan="2"><strong>CATEGORY TABLE</strong></td>
		</tr>
		<tr>
			<td>C_NO</td>
			<td>C_NAME</td>
		<tr>
			<%
				for (int categoryIndex = 0; categoryIndex < maxCategoryIndex; categoryIndex++) {
					int    c_no   = categoryList.get(categoryIndex).getC_no();
					String c_name = categoryList.get(categoryIndex).getC_name();
			%>
		
		<tr>
			<td><%=c_no %></td>
			<td><%=c_name %></td>
		</tr>
			<%
				}
			%>
	</table>
</body>
</html>