<%@ page  contentType="text/html; charset=EUC-KR"%>
<%@ page import = "logon.LogonDBBean" %>
 
<html>
<head>
<title>회원탈퇴</title>
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
				<font><b>회원정보가 삭제되었습니다.</b></font>
			</td>
		</tr>
	    <tr>	
	    	<td align="center">
	    		<p>안녕히 가십시요.</p>
	    		<meta http-equiv="refresh" content="5;url=main.jsp">
	    	</td>
	    </tr>
	    <tr>
	    	<td align="center">
	    		<input type="submit" value="확인">
	    	</td>
	    </tr>
	</table>
</form>
<%}else {%>
	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
<%} %>
</body>
</html>