<%@page import="java.util.List"%>
<%@page import="order.OrderDAO"%>
<%@page import="product.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%
	OrderDAO orderManager = OrderDAO.getInstance();
	List<Integer> orderNoList = orderManager.selectsOrder_NO();
	int orderlistCount = orderNoList.size();

	ProductDAO productManager = ProductDAO.getInstance();
	List<Integer> productNoList = productManager.selectsProduct_NO();
	int productlistCount = productNoList.size();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>INSERT ORDER&ORDER_PRODUCT</title>
</head>
<body>
	<form action="inserted_mix.jsp" method="post">
		<table border="1">
			<tr>
				<td>O_MNO</td>
				<td><input type="text" name="O_MNO"></td>
			</tr>
			<tr>
				<td>O_PAY</td>
				<Td>
					<select name="O_PAY">
						<option value="0">주문대기 </option>
						<option value="1">주문취소 </option>
						<option value="2">주문확인 </option>
						<option value="3">환불</option>
						<option value="4">구매확정</option>
					</select>
				</Td>
			</tr>
			<tr>
				<td rowspan="2" >PRODUCT</td>
				<td>OP_PNO</td>
				<td>OP_COUNT</td>
			</tr>
			<tr>
				<td>
					<select name="OP_PNO">
					<%
						for(int i=0; i<productlistCount; i++) {
					%>
						<option value="<%=productNoList.get(i)%>"><%=productNoList.get(i) %></option>
					<%
						}
					%>
					</select>
				</td>
				<td><input type="number" min="1" max="10" required="required" name="OP_COUNT"></td>
			<tr>
				<td>O_ADRESS</td>
				<td><input type="text" name="O_ADDRESS"></td>
			</tr>
			<tr>
				<td width="2"><input type="submit" value="INSERT"></td>
			</tr>
		</table>
	</form>
</body>
</html>