<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="product.ProductDAO"%>
<%@ page import="product.ProductDTO"%>
<%@ page import="java.util.List"%>

<%
	int p_no = 0;

    if(request.getParameter("p_no") != null
    		&& !"".equals(request.getParameter("p_no"))) {
    	p_no = Integer.parseInt(request.getParameter("p_no"));
    }
%>

<%
	ProductDAO dbPro = ProductDAO.getInstance();

	ProductDTO productEntity = new ProductDTO();

	productEntity = dbPro.getProduct(p_no);
			
%>
<html>
<head>
<title>상품 상세보기</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<form method="post" action="product_cart.jsp">
<table border="1" width="1500" align="center">
	<tr>
		<td height="100" align="center" colspan="2" align="center">
				<jsp:include page = "/shoppingmall/mainTop.jsp" />
		</td>
	</tr>
	
	<table border="1" width="1200" cellpadding="0" cellspacing="0" align="center">
			<tr height="200">
				<td align="center">
					<!-- <img src="/shoppingmall/save/<%=productEntity.getP_path()%>"style="width:400px; height:400px;" /> -->
					<img src="/shoppingmall/img/1.gif" style="width:400px; height:500px;" /><br>
					
				</td>
		
				<td valign="top" width="500" height="350" align="center"> 
				<br/>
			<h2><b><%=productEntity.getP_name()%></b></h2>
			<br/><br/>
			<b>수량&nbsp;:&nbsp;<b><select name="quantity">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
				<option>7</option>
				<option>8</option>
				<option>9</option>
				<option>10</option>
			</select>
			
			<div align="center">
			<br/>			
			<b>상품가격&nbsp;:&nbsp;<%=productEntity.getP_price() %>원</b>
			<br/><br/>
			
			<input type="submit" value="장바구니"> &nbsp;
			<input type="button" value="취소"  onclick="javascript:window.location='ex1.jsp'"> &nbsp;
												
			
			<br/><br/>
			<b>상품설명</b><br/><br/>
			<%=productEntity.getP_desc() %>
			
			</div>
			
			</td>
			
			</tr>
			
		</table>

	
	<td>
	</tr>
	<tr>
			<td height="100" align="center" colspan="2">
				<jsp:include page="/shoppingmall/bottom.jsp"/>		
			</td>
	</tr>
</table>
</form>
</body>
</html>