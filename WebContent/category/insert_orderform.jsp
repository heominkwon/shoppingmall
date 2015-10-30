<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>INSERT ORDER</title>
</head>
<body>
	<form action="inserted_order.jsp" method="post">
		<table>
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
				<td>O_ADRESS</td>
				<td><input type="text" name="O_ADDRESS"></td>
			</tr>
			<tr>
				<td><input type="submit" value="INSERT"></td>
			</tr>
		</table>
	</form>
</body>
</html>