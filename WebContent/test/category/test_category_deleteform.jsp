<%@page import="category.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="category.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>delete category</title>
</head>
<body>
<%
	CategoryDAO 	  categoryManager    = CategoryDAO.getInstance();
	List<Integer>	  c_noList           = categoryManager.selectC_noAll();
	int 			  maxC_noIndex       = categoryManager.getCategoryCount();
	
	List<CategoryDTO> categoryList	     = categoryManager.selectCategoryAll();
	int 			  maxCategoryIndex   = categoryManager.getCategoryCount();
%>
	<a href="test_category_main.jsp">back</a>
	
	<form action="test_category_deletepro.jsp">
		<table border="1">
			<tr>
				<td>C_NO</td>
			</tr>
			<tr>
				<td>
					<select name="C_NO">
						<%
							for(int c_noIndex=0; c_noIndex<maxC_noIndex; c_noIndex++) {
						%>
							<option value="<%=c_noList.get(c_noIndex)%>"><%=c_noList.get(c_noIndex) %></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="DELETE"></td>
				<td><input type="reset"  value="CANCEL"></td>
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