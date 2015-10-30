<%@page import="OrderProduct.OrderProductDTO"%>
<%@page import="OrderProduct.OrderProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="order.OrderDAO"%>
<%@page import="order.OrderDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	OrderDAO OrderManager = OrderDAO.getInstance();
	List<OrderDTO> orderList = OrderManager.selectsOrder();
	int orderlistCount = orderList.size();
	
	OrderProductDAO OrderProductManager = OrderProductDAO.getInstace();
	List<OrderProductDTO> orderproductList = OrderProductManager.selectsOrderProduct();
	int OP_listCount = orderproductList.size();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>order</title>
</head>
<body>
	<a href="order.jsp">ordertest</a>
	<a href="top.jsp">top</a>
	<a href="inner.jsp">inner</a>
	<p/>
	<a href="insert_orderform.jsp">INSERT ORDER</a>
	<p />
	<a href="insert_orderproductform.jsp">INSERT ORDER_PRODUCT</a>
	<p />
	<a href="insert_mixform.jsp">INSERT ORDER&ODER_PRODUCT</a>
<div style="float: left">
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
			for (int i = 0; i < orderlistCount; i++) {
		%>
		<tr>
			<td><%=orderList.get(i).getO_no()%></td>
			<td><%=orderList.get(i).getO_mno()%></td>
			<td><%=orderList.get(i).getO_pay()%></td>
			<td><%=orderList.get(i).getO_address()%></td>
			<td><%=orderList.get(i).getO_date()%></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
<div style="float: left">
	<table border="1">
		<tr>
			<td colspan="5" align="center"><strong>ORDER_PRODUCT</strong></td>
		</tr>
		<tr align="center">
			<td>주문상품번호</td>
			<td>주문내역번호</td>
			<td>상품번호</td>
			<td>상품수량</td>
			<td>가격</td>
		</tr>
		<tr align="lef">
			<td>OP_NO</td>
			<td>OP_ONO</td>
			<td>OP_PNO</td>
			<td>OP_COUNT</td>
			<td>OP_PRICE</td>
		</tr>
		<%
			for (int i = 0; i < OP_listCount; i++) {
		%>
		<tr>
			<td><%=orderproductList.get(i).getOP_no()%></td>
			<td><%=orderproductList.get(i).getOP_ono()%></td>
			<td><%=orderproductList.get(i).getOP_pno()%></td>
			<td><%=orderproductList.get(i).getOP_count()%></td>
			<td><%=orderproductList.get(i).getOP_price()%></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
</body>
</html>