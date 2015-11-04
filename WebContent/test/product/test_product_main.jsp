<%@page import="product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="product.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>TEST PRODUCT MAIN</title>
</head>
<body>
<%
	ProductDAO productManager = ProductDAO.getInstance();
	List<ProductDTO> productList = productManager.selectProductAll();
	int maxProductIndex = productManager.getProductCount();

%>
	<a href="../test_main.jsp">HOME</a>
	<p />
	<a href="test_product_insertform.jsp">insert</a>
	<p />
	<a href="test_product_deleteform.jsp">delete</a>
	<p />
	<a href="test_product_updateform.jsp">update</a>
	<p />
	
	<table border="1">
		<tr>
			<td colspan="8" align="center"><strong>PRODUCT</strong></td>
		</tr>
		<tr>
			<td>P_NO</td>
			<td>P_CNO</td>
			<td>P_NAME</td>
			<td>P_PRICE</td>
			<td>P_COUNT</td>
			<td>P_DESC</td>
			<td>P_PATH</td>
			<td>P_REGDATE</td>
		</tr>
		<%
			for(int productIndex=0; productIndex<maxProductIndex; productIndex++) {
		%>
			<tr>
				<td><%=productList.get(productIndex).getP_no() %></td>
				<td><%=productList.get(productIndex).getP_cno() %></td>
				<td><%=productList.get(productIndex).getP_name() %></td>
				<td><%=productList.get(productIndex).getP_price() %></td>
				<td><%=productList.get(productIndex).getP_count() %></td>
				<td><%=productList.get(productIndex).getP_desc() %></td>
				<td><%=productList.get(productIndex).getP_path() %></td>
				<td><%=productList.get(productIndex).getP_regdate() %></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>