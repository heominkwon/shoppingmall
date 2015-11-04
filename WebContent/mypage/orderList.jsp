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
<center><b>�ֹ�����/�����Ȳ</b></center>
<%
    if(count == 0){// �ֹ������� ������
%>
<table width="700" cellspacing="0" border="1" >
<tr>
	<td align="center">
		�ֹ��� ��ǰ�� �����ϴ�.
	</td>
</table>

<% }else{ %>
<table width = "700" border="1" cellspacing="0">
	<tr>
		<td width = "100">�ֹ�����</td>
		<td width = "300">��ǰ��</td>
		<td width = "200">�����ݾ�(����)</td>
		<td width = "100">�ֹ�����</td>
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
    if (count > 0) {//�Խñ��� �����Ѵٸ�
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 	/*�������� ������ �����ϴ� pageCount ��������
		 	�������� ������ �������� �ִ� �Խñ� ���� �̿��Ͽ� ����� ����*/
        int startPage = (int)(currentPage/10)*10+1; 
		 	//���� �������� �����ִ� ������������� ù��° ������ ���� �����ϴ� ���� 
		int pageBlock=10;//�� ȭ�� �ִ� ������ ��(������ ��� �ִ� ����)
        int endPage = startPage + pageBlock-1;
      		//���� �������� �����ִ� ������������� ������ ������ ���� �����ϴ� ����
        if (endPage > pageCount) endPage = pageCount;
        	//���� �������������� ���ڰ� ���������� �������� ���ٸ� �������������� ���ڴ� ���������� �����ȴ�.
        if (startPage > 10) {    %>
        <a href="new_order.jsp?pageNum=<%= startPage - 10 %>">[����]</a>
<%      }
        //���� ��ŸƮ ������ ���ڰ� 10���ʰ� �Ѵٸ� ��ŸƮ ���������� -10�� ���������� �̵� �� ���ִ� 
        //[����]��ũ�� ����� ��������ũ���� ��������� ���ʿ� ���� ����� 
        for (int i = startPage ; i <= endPage ; i++) {
        //�������������� ���������������� �� �������� �̵��� �� �ִ� ��������ũ���� �����Ѵ�	
        %>
        <a href="new_order.jsp?pageNum=<%= i %>">[<%= i %>]</a>
        
<%
        }
        if (endPage < pageCount) {  %>
         
        <a href="new_order.jsp?pageNum=<%= startPage + 10 
        //���� ������ ������ ���ڰ� ������ �Ѱ����� ���ں��� �۴ٸ� ��ŸƮ ���������� +10�� ���������� �̵� �� ���ִ�
        //[����]��ũ�� ����� ��������ũ���� �����Ŀ� �����ʿ� �����
        %>">[����]</a>
<%
        }
    }
%>
</center>
</body>
</html>