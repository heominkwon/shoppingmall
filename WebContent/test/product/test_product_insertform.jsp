<%@page import="java.util.List"%>
<%@page import="category.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>insert product</title>
</head>
<body>
<%
	CategoryDAO   categoryManager = CategoryDAO.getInstance();
	List<Integer> c_noList        = categoryManager.selectC_noAll();
	int           maxC_noIndex    = categoryManager.getCategoryCount();
%>
	<form action="test_product_insertpro.jsp">
		<table border="1">
			<tr>
				<td>P_CNO</td>
				<td>P_NAME</td>
				<td>P_PRICE</td>
				<td>P_COUNT</td>
				<td>P_DESC</td>
				<td>P_PATH</td>
				<td>P_REGDATE</td>
			</tr>
			<tr>
				<td>
					<select>
						<%
							for (int c_noIndex=0; c_noIndex<maxC_noIndex; c_noIndex++) {
						%>
							<option value="<%=c_noList.get(c_noIndex)%>"><%=c_noList.get(c_noIndex) %></option>
						<%
							}
						%>
					</select>
				</td>
				<td><input type="text" name="P_NAME"></td>
				<td><input type="text" name="P_PRICE"></td>
				<td><input type="number" min="1" required="required" name="P_COUNT"></td>
				<td><input type="text" name="p_desc"></td>
				<td><input type="text" name="p_path"></td>
				<td><input type="text" name="p_regdate"></td>
			</tr>
			<tr>
				<td><input type="submit" value="INSERT"></td>
				<td><input type="reset"  value="CANCEL"></td>
			</tr>
		</table>
	</form>
</body>
</html>