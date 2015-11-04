<%@ page contentType="text/html;charset=euc-kr" %>
<%@ page import = "project1.logon.LogonDBBean" %>

<html>
<title>ID 중복확인</title>
<% request.setCharacterEncoding("euc-kr");%>

<%

    String id = request.getParameter("id"); //get방식으로 보냈죠
   LogonDBBean manager = LogonDBBean.getInstance();
    int check= manager.confirmId(id);
 
 %>
<%
    if(check == 1) {
%>
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr bgcolor="skyblue"> 
    <td height="39" ><%=id%>이미 사용중인 아이디입니다.</td>
  </tr>
</table>
<form name="checkForm" method="post" action="confirmId.jsp">
<table>
  <tr>
    <td bgcolor="skyblue" align="center"> 
       다른 아이디를 선택하세요.<p>
       <input type="text" maxlength="12" name="id"> 
       <input type="submit" value="ID중복확인">
    </td>
  </tr>
</table>
</form>
<%
    } else {
%>

<table>
  <tr bgcolor="skyblue"> 
    <td align="center"> 
      <p>입력하신 <%=id%> 는 사용하실 수 있는 ID입니다.  </p>
      <input type="button" value="닫기" onclick="setid()">
    </td>
  </tr>
</table>
<%
    }
%>

</html>
<script language="javascript">

  function setid()
    {		
    	opener.document.userinput.id.value="<%=id%>";
		self.close();
    }
		
</script>
