<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "order.OrderDAO" %>
<%@ page import = "order.OrderDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String pageNum=request.getParameter("pageNum");
	int o_no=Integer.parseInt(request.getParameter("o_no").toString());
	int o_pay=Integer.parseInt(request.getParameter("o_pay").toString());
			
			OrderDAO dbpro = OrderDAO.getInstance();
			
			if(o_pay==0){
			dbpro.updatePAY_NO(o_no, 1);
			}
			else if(o_pay==2){
			dbpro.updatePAY_NO(o_no, 3);
			}
%>
<body>

<meta http-equiv="Refresh" content="0;url=cancle_order.jsp?pageNum=<%=pageNum%>" >
</body>
</html>