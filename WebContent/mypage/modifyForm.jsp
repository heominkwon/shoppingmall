<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page import = "logon.*" %>


<html>
<head>
<title>회원정보수정</title>
</head>

<%
	String id = (String)session.getAttribute("memId");
	LogonDBBean manager = LogonDBBean.getInstance();
	LogonDataBean c = manager.getMember(id);
	
	try{
%>

<body>
<form method="post" action= "modifyPro.jsp" name="userinput" onsubmit="return checkIt()">

	<table width="600" border="1" cellspacing="0">
		<tr>
			<td colspan="2" align="center">
				<font><b>회원정보 수정</b></font>
			</td>
		</tr>
		<tr>
		<tr>
			<td>이름 입력</td>
			<td>
			<%=c.getName()%>
				<input type="hidden" name="name" value="<%=c.getName() %>">
			</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>
			<%=c.getId() %>
				<input type="hidden" name="id" value="<%=c.getId() %>">
								
			</td>  
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="passwd" maxlength="12">
			</td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td>
				<input type="password" name="passwd2" maxlength="12">
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
			    <input type="text" name="birth" maxlength="6" value="<%=c.getBirth()%>">
			   
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
			 
			    <input type="text" name="address" maxlength="50" value="<%=c.getAddress()%>">
				
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
			
			    <input type="text" name="phone" maxlength="13" value="<%=c.getPhone()%>">
			
			
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>
			  <%if(c.getEmail()==null){%>
				<input type="text" name="email" maxlength="30">
			  <%}else{ %>
			    <input type="text" name="email" maxlength="30" value="<%=c.getEmail()%>">
			  <%} %>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" >
				<input type="submit" name="modify" value="정보수정">
				<input type="button" value="취소" onclick="javascript:window.loaction='main.jsp'">
				
			</td>
		</tr>
	</table>
	</form>
</body>
<%}catch(Exception e){}%>
</html>