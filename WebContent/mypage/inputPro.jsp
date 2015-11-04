<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page import = "project1.logon.LogonDBBean" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id="member" class="project1.logon.LogonDataBean">
	<jsp:setProperty name="member" property="*"/>
</jsp:useBean>

<%
	LogonDBBean manager = LogonDBBean.getInstance();
	manager.insertMember(member);
	
	response.sendRedirect("main.jsp");
%>