<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>전체 상품 목록</title>
</head>
<body>
	<table border="0" width="1500" align="center">
	<tr>
		<td height="100" align="center" colspan="2" align="center">
				<jsp:include page = "/shoppingmall/mainTop.jsp" />
		</td>
	</tr>
	<tr>
			<td valign="top" width="200">
 				<jsp:include page="index.jsp" flush="false" />
	
			</td>
		<td>
				<jsp:include page = "/shoppingmall/product_by_category.jsp" />
		</td>
	</tr>
	<tr>
			<td height="50" colspan="2" align="center">
				<jsp:include page="/shoppingmall/bottom.jsp"/>		
			</td>
	</tr>
	</table>
</body>
</html>