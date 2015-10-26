<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="order.OrderDTO"%>
<%@page import="order.OrderDAO"%>
<%@page import="product.ProductDAO"%>
<%@page import="OrderProduct.OrderProductDTO"%>
<%@page import="OrderProduct.OrderProductDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%
	
	request.setCharacterEncoding("euc-kr");

	OrderProductDAO orderproductManager = OrderProductDAO.getInstace();
	ProductDAO productManager = ProductDAO.getInstance();
	OrderDAO orderManager = OrderDAO.getInstance();
	
	String O_MNO = request.getParameter("O_MNO");
	String O_PAY = request.getParameter("O_PAY");
	String O_ADDRESS = request.getParameter("O_ADDRESS");
	Date   O_DATE    = new Date(System.currentTimeMillis());
	
	int OP_PNO = Integer.parseInt(request.getParameter("OP_PNO"));
	int OP_COUNT = Integer.parseInt(request.getParameter("OP_COUNT"));
	int P_PRICE = productManager.selectPrice_nNO(OP_PNO);
	int OP_PRICE = OP_COUNT * P_PRICE;
	
	OrderDTO order = new OrderDTO(O_MNO, O_PAY, O_ADDRESS, O_DATE);
	OrderProductDTO order_product = new OrderProductDTO(OP_PNO, OP_COUNT, OP_PRICE);
	
	orderManager.insertOrder(order);
	orderproductManager.insertOrderProduct(order_product);
	
	response.sendRedirect("order.jsp");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>