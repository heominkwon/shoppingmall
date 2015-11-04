<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "OrderProduct.OrderProductDAO" %>
<%@ page import = "OrderProduct.OrderProductDTO" %>
<%@ page import = "order.OrderDAO" %>
<%@ page import = "order.OrderDTO" %>
<%@ page import = "product.ProductDTO" %>
<%@ page import = "product.ProductDAO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<% request.setCharacterEncoding("euc-kr");%>
<%
	int pageSize = 10;
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){
		pageNum = "1";
	}
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage - 1) *pageSize +1;
	int endRow = currentPage * pageSize;
	int count = 0;
	
	List orderList = null;
	OrderDAO od = OrderDAO.getInstance();
	ProductDAO pd = ProductDAO.getInstance();
	//OrderProductDAO opd = OrderProductDAO.getInstance();
	count = od.getNewOrderCount();
	if(count > 0){
		orderList = od.selectsNewOrder(startRow, endRow);
	}
%>


<body>
<center><b>주문내역/배송현황</b></center>
<%
    if(count == 0){// 주문내역이 없으면
%>
<table width="700" cellspacing="0" border="1" >
<tr>
	<td align="center">
		주문한 상품이 없습니다.
	</td>
</table>

<% }else{ %>
<table width = "700" border="1" cellspacing="0">
	<tr>
		<td width = "100">주문일자</td>
		<td width = "300">상품명</td>
		<td width = "200">결제금액(수량)</td>
		<td width = "100">주문상태</td>
	</tr>
<%
	for(int i=0 ; i <orderList.size() ; i++){
		OrderDTO order = (OrderDTO)orderList.get(i);
%>	
	<tr>
		<td align = "center" width = "100"><%=order.getO_date()%></td>
		<td align = "center" width = "300">pd.getP_name()</td>
		<td align = "center" width = "200">opd.getOP_price()</td>
		<td align = "center" width = "100"><%=order.getO_pay() %></td>
	</tr>
<%}%> 
</table>
<% }
    if (count > 0) {//게시글이 존재한다면
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 	/*페이지의 갯수를 저장하는 pageCount 변수생성
		 	총페이지 갯수와 한페이지 최대 게시글 수를 이용하여 계산해 저장*/
        int startPage = (int)(currentPage/10)*10+1; 
		 	//현재 페이지가 속해있는 페이지블락에서 첫번째 페이지 숫자 저장하는 변수 
		int pageBlock=10;//한 화면 최대 페이지 수(페이지 블락 최대 갯수)
        int endPage = startPage + pageBlock-1;
      		//현재 페이지가 속해있는 페이지블락에서 마지막 페이지 숫자 저장하는 변수
        if (endPage > pageCount) endPage = pageCount;
        	//만약 마지막페이지의 숫자가 총페이지의 갯수보다 높다면 마지막페이지의 숫자는 총페이지의 수가된다.
        if (startPage > 10) {    %>
        <a href="new_order.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
<%      }
        //만약 스타트 페이지 숫자가 10을초과 한다면 스타트 페이지에서 -10을 한페이지로 이동 할 수있는 
        //[이전]링크를 만든다 페이지링크들을 만들기전에 왼쪽에 먼저 만든다 
        for (int i = startPage ; i <= endPage ; i++) {
        //시작페이지부터 마지막페이지까지 각 페이지로 이동할 수 있는 페이지링크들을 생성한다	
        %>
        <a href="new_order.jsp?pageNum=<%= i %>">[<%= i %>]</a>
        
<%
        }
        if (endPage < pageCount) {  %>
         
        <a href="new_order.jsp?pageNum=<%= startPage + 10 
        //만약 마지막 페이지 숫자가 페이지 총갯수에 숫자보다 작다면 스타트 페이지에서 +10을 한페이지로 이동 할 수있는
        //[다음]링크를 만든다 페이지링크들을 만든후에 오른쪽에 만든다
        %>">[다음]</a>
<%
        }
    }
%>
</center>
</body>
</html>