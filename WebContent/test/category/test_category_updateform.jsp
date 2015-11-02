<%@page import="java.util.List"%>
<%@page import="category.CategoryDAO"%>
<%@page import="category.CategoryDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>update category</title>
</head>
<body>
	<%
	CategoryDAO 	  categoryManager    = CategoryDAO.getInstance();
	List<CategoryDTO> categoryList	     = categoryManager.selectCategoryAll();
	int 			  maxCategoryIndex   = categoryManager.getCategoryCount();
	
	List<Integer>	  c_noList           = categoryManager.selectC_noAll();
	int          	  maxC_noIndex       = categoryManager.getCategoryCount();
%>
	<a href="test_category_main.jsp">back</a>

	<form action="test_category_updatepro.jsp">
		<table border="1">
			<tr>
				<td>C_NO</td>
				<td>C_NAME</td>
			</tr>
			<tr>
				<td>
					<select name="C_NO">
						<%
							for (int c_noIndex=0; c_noIndex<maxC_noIndex; c_noIndex++) {
						%>
						<option value="<%=c_noList.get(c_noIndex)%>"><%=c_noList.get(c_noIndex) %></option>
						<%
							}
						%>
					</select>
				</td>
				<td><input type="text" name="C_NAME"></td>
			</tr>
			<tr>
				<td><input type="submit" value="UPDATE"></td>
				<td><input type="reset"  value="cancel"></td>
			</tr>
		</table>
	</form>
	
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