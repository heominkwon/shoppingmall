<%@page import="java.util.List"%>
<%@page import="order.OrderDAO"%>
<%@page import="order.OrderDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	OrderDAO daoManager = OrderDAO.getInstance();
	List<OrderDTO> dtoList = daoManager.selectsOrder();
	int listCount = dtoList.size();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>order</title>
</head>
<body>
	<a href="insert_orderform.jsp">INSERT ORDER</a>
	<p />
	<a href="insert_orderproductform.jsp">INSERT ORDER_PRODUCT</a>

	<table border="1" >
		<tr>
			<td colspan="5" align="center"><strong>ORDERS</strong></td>
		</tr>
		<tr align="center">
			<td>주문내역번호</td>
			<td>고객번호</td>
			<td>결제상태</td>
			<td>배송지</td>
			<td>주문일자</td>
		</tr>
		<tr align="lef">
			<td>O_NO</td>
			<td>O_MNO</td>
			<td>O_PAY</td>
			<td>O_ADDRESS</td>
			<td>O_DATE</td>
		</tr>
		<%
			for (int i = 0; i < listCount; i++) {
		%>
		<tr>
			<td><%=dtoList.get(i).getO_no()%></td>
			<td><%=dtoList.get(i).getO_mno()%></td>
			<td><%=dtoList.get(i).getO_pay()%></td>
			<td><%=dtoList.get(i).getO_address()%></td>
			<td><%=dtoList.get(i).getO_date()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>