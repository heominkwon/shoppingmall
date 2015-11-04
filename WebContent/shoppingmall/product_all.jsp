<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "product.ProductDAO" %>
<%@ page import = "product.ProductDTO" %>
<%@ page import = "java.util.List" %>

<%
    int pageSize = 20;                       	
%>

<%
    String pageNum = request.getParameter("pageNum"); 	  
    if (pageNum == null) {								
        pageNum = "1";
    }
  
    int currentPage = Integer.parseInt(pageNum);   		
    int startRow = (currentPage - 1) * pageSize + 1;   
    int endRow = currentPage * pageSize;
    int count = 0;
    int number=0;

    List productList = null;										
    ProductDAO dbPro = ProductDAO.getInstance();
    
    count = dbPro.getProductCount();							
    if (count > 0) {   											
        productList = dbPro.getProducts(startRow, endRow);
    }																

	number=count-(currentPage-1)*pageSize;				
%>		
<html>
<head>
<title>상품목록</title>
<link href="style.css" rel="stylesheet" type="text/css">    
</head>

<script lang="javascript">

function goProductDetailPage(p_no) {
	var objForm = document.product_detail;

	objForm.p_no.value = p_no;

	objForm.action = "/shoppingmall/shoppingmall/product_detail.jsp";		
	objForm.submit();
}
</script>

<body>



<form name="product_detail" action="" method="post">
	<input type="hidden" name="p_no" value="" />
</form>

<center><b>상품목록</b>

<%
    if (count == 0) {		
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">

<tr>
    <td align="center">
    상품이 없습니다.
    </td>
</table>

<%  } else {    %>   
<table border="1" width="800" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="200">    
	<%
			for(int i=0; i<productList.size(); i++){
				ProductDTO productEntity = (ProductDTO)productList.get(i);
				if(i>0 && i%4==0){
	%>
	
	</tr><tr height="200">
	
	
				
			
	<%}%>
			<td>
				<a href="#" onclick="javascript:goProductDetailPage('<%=productEntity.getP_no()%>')">
					<!-- <img src="/shoppingmall/save/<%=productEntity.getP_path()%>"style="width:200px; height:200px;" /> -->
					<img src="/shoppingmall/img/<%=i+1%>.gif" style="width:300px; height:300px;" />
				</a>
				<br>
				<%=productEntity.getP_name()%><br>
				<%=productEntity.getP_price()%>
			</td>
	
		

  
     <%}%>
     </tr>
</table>


<%
    if (count > 0) {
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
     
        int startPage = (int)(currentPage/10)*10+1;
      
		int pageBlock=10;
        int endPage = startPage + pageBlock-1;
      
        if (endPage > pageCount) endPage = pageCount;
     
        if (startPage > 10) {    %>
        <a href="list.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
<%      }
      
        for (int i = startPage ; i <= endPage ; i++) {  
%>
        <a href="list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
        }
        if (endPage < pageCount) {  %>
        <a href="list.jsp?pageNum=<%= startPage + 10 
      
        %>">[다음]</a>
<%
        }
    }
			}

%>
</center>

</body>
</html>