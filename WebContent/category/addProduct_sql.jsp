<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="product.*"%>
<%@ page import="java.sql.Timestamp"%>

<% request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="product" class="product.ProductDTO">
	<jsp:setProperty name="product" property="*"/>
</jsp:useBean>

<%
	ProductDAO product_interface = ProductDAO.getInstance();
	product.setP_no(request.getParameter("product_no"));
	product_interface.insertProduct(product);
%>