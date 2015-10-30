<%@page import="java.util.ArrayList"%>
<%@page import="product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="product.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>bottom</title>
</head>

<body>
	<a href="order.jsp">ordertest</a>
	<a href="top.jsp">top</a>
	<a href="inner.jsp">inner</a>
	<%
		int bottom_no = 1;
		ProductDAO productManager = ProductDAO.getInstance();
		List<ProductDTO> bottomList = new ArrayList<ProductDTO>();
		bottomList = productManager.selectsProduct_CNO(bottom_no);
		int bottomListSize = bottomList.size();
	%>

	<table border="1">
		<%
			for (int i = 0; i < bottomListSize; i++) {
		%>
		<tr>
			<td>name : <%=bottomList.get(i).getP_name()%><br /> price : <%=bottomList.get(i).getP_count()%><br />
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
