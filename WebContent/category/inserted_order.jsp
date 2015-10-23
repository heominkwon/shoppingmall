<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="order.OrderDTO"%>
<%@page import="order.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%

	request.setCharacterEncoding("euc-kr");
	OrderDAO orderManager = OrderDAO.getInstance();
	
	String O_MNO = request.getParameter("O_MNO");
	String O_PAY = request.getParameter("O_PAY");
	String O_ADDRESS = request.getParameter("O_ADDRESS");
	Date   O_DATE    = new Date(System.currentTimeMillis());

	OrderDTO dto = new OrderDTO(O_MNO, O_PAY, O_ADDRESS, O_DATE);
	
	orderManager.insertOrder(dto);
		
	response.sendRedirect("order.jsp");
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<body>

</body>
</html>