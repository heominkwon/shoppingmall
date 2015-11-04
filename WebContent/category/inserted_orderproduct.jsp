<%@page import="OrderProduct.OrderProductDTO"%>
<%@page import="product.ProductDAO"%>
<%@page import="OrderProduct.OrderProductDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%
	
	request.setCharacterEncoding("euc-kr");
	OrderProductDAO orderproductManager = OrderProductDAO.getInstace();
	ProductDAO productManager = ProductDAO.getInstance();
	
	int OP_ONO = Integer.parseInt(request.getParameter("OP_ONO"));
	int OP_PNO = Integer.parseInt(request.getParameter("OP_PNO"));
	int OP_COUNT = Integer.parseInt(request.getParameter("OP_COUNT"));
	int P_PRICE = productManager.selectPrice_nNO(OP_PNO);
	int OP_PRICE = OP_COUNT * P_PRICE;
	
	System.out.println("OP_ONO"+OP_ONO);
	System.out.println(OP_PNO);
	System.out.println(OP_COUNT);
	System.out.println(P_PRICE);
	System.out.println(OP_PRICE);
	
	OrderProductDTO dto = new OrderProductDTO(OP_ONO, OP_PNO, OP_COUNT, OP_PRICE);
	
	orderproductManager.insertOrderProduct(dto);
	
	response.sendRedirect("order.jsp");
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<body>

</body>
</html>