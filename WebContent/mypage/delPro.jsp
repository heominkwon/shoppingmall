<%@ page  contentType="text/html; charset=EUC-KR"%>
<%@ page import = "logon.LogonDBBean" %>
 
<html>
<head>
<title>ȸ��Ż��</title>
</head>
<%
	String id = (String)session.getAttribute("memId");
	String passwd = request.getParameter("passwd");
	

	LogonDBBean manager = LogonDBBean.getInstance();
	int check = manager.deleteMember(id,passwd);
	
	if(check==1){
		session.invalidate();
	%>
<body>
<form method="post" action="main.jsp" name="userinput">
	<table cellspacing="0" cellpadding="5" align="center">
		<tr>
			<td align="center"> 
				<font><b>ȸ�������� �����Ǿ����ϴ�.</b></font>
			</td>
		</tr>
	    <tr>	
	    	<td align="center">
	    		<p>�ȳ��� ���ʽÿ�.</p>
	    		<meta http-equiv="refresh" content="5;url=main.jsp">
	    	</td>
	    </tr>
	    <tr>
	    	<td align="center">
	    		<input type="submit" value="Ȯ��">
	    	</td>
	    </tr>
	</table>
</form>
<%}else {%>
	<script>
		alert("��й�ȣ�� ���� �ʽ��ϴ�.");
		history.go(-1);
	</script>
<%} %>
</body>
</html>