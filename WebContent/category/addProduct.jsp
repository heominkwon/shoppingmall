<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>addProduct</title>
</head>

<body>
	<form action="addProduct_sql.jsp" method="post">
		<table>
			<tr>
				<td>번호</td>
				<td><input type="text" name="product_no" value=""></td>
			</tr>
			<tr>
				<td>품목</td>
				<td>
					<select class="" name="category">
						<option value="0">TOP</option>
						<option value="1">BOTTOM</option>
						<option value="2">INNER</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="product_name" value=""/>	
				</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>
					<input type="text" name="product_price" value=""/>
				</td>
			</tr>
			<tr>
				<td>수량</td>
				<td>
					<input type="text" name="product_count" value=""/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="등록"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>