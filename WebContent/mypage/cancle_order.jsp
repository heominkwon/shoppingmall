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
	int pageSize = 20;
	SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");

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
	OrderProductDAO opd = OrderProductDAO.getInstance();
	count=od.getMemOrderCount(69); 
	//count = od.getMemOrderCount(Integer.parseInt(session.getAttribute("m_no").toString()));
	if(count > 0){
		orderList = od.selectsMemOrder(startRow, endRow, 69);
		//orderList = od.selectsMemOrder(startRow, endRow, Integer.parseInt(session.getAttribute("m_no").toString()));
	}
%>


<body>
<center><b>�ֹ����/ȯ��</b>
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
<table width = "650" border="1" cellspacing="0">
	<tr align="center">
		<td width = "100">�ֹ�����</td>
		<td width = "250">��ǰ��</td>
		<td width = "200">�����ݾ�(����)</td>
		<td width = "100">�ֹ�����</td>
	</tr>
<%
	for(int i=0 ; i <orderList.size() ; i++){
		OrderDTO order = (OrderDTO)orderList.get(i);
		if(order.getO_pay()==1 || order.getO_pay()==3||order.getO_pay()==4)
			continue;

%>	
	<tr>
		<td align = "center" width = "100"><%=order.getO_date()%></td>
		<% OrderProductDTO opp = opd.selectOrderProduct2(order.getO_no());%>
		<td align = "center" width = "250"><%=pd.getProductName(opp.getOP_pno())%></td>
		<td align = "center" width = "200"><%=opp.getOP_price()%>��(����: <%=opp.getOP_count()%>)</td>
		<td align = "center" width = "100"><%if(order.getO_pay()==0) {%>
		<a href="cancelPro.jsp?pageNum=<%=pageNum%>&o_no=<%=order.getO_no()%>&o_pay=<%=order.getO_pay()%>">�ֹ����</a>
		<% }else if(order.getO_pay()==1){%>
		��ҿ�û
		<% }else if(order.getO_pay()==2){%>
		<a href="cancelPro.jsp?pageNum=<%=pageNum%>&o_no=<%=order.getO_no()%>&o_pay=<%=order.getO_pay()%>">ȯ�ҿ�û</a>
		<% }else if(order.getO_pay()==3){%>
		ȯ�ҿ�û
		<% }else{%>
		���ſϷ�
		<%}%>
		</td>
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
        <a href="cancle_order.jsp?pageNum=<%= startPage - 10 %>">[����]</a>
<%      }
        //���� ��ŸƮ ������ ���ڰ� 10���ʰ� �Ѵٸ� ��ŸƮ ���������� -10�� ���������� �̵� �� ���ִ� 
        //[����]��ũ�� ����� ��������ũ���� ��������� ���ʿ� ���� ����� 
        for (int i = startPage ; i <= endPage ; i++) {
        //�������������� ���������������� �� �������� �̵��� �� �ִ� ��������ũ���� �����Ѵ�	
        %>
        <a href="cancle_order.jsp?pageNum=<%= i %>">[<%= i %>]</a>
        
<%
        }
        if (endPage < pageCount) {  %>
         
        <a href="cancle_order.jsp?pageNum=<%= startPage + 10 
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