<%@ page contentType="text/html;charset=euc-kr" %>
<%@ page import = "project1.logon.LogonDBBean" %>

<html>
<title>ID �ߺ�Ȯ��</title>
<% request.setCharacterEncoding("euc-kr");%>

<%

    String id = request.getParameter("id"); //get������� ������
   LogonDBBean manager = LogonDBBean.getInstance();
    int check= manager.confirmId(id);
 
 %>
<%
    if(check == 1) {
%>
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr bgcolor="skyblue"> 
    <td height="39" ><%=id%>�̹� ������� ���̵��Դϴ�.</td>
  </tr>
</table>
<form name="checkForm" method="post" action="confirmId.jsp">
<table>
  <tr>
    <td bgcolor="skyblue" align="center"> 
       �ٸ� ���̵� �����ϼ���.<p>
       <input type="text" maxlength="12" name="id"> 
       <input type="submit" value="ID�ߺ�Ȯ��">
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
      <p>�Է��Ͻ� <%=id%> �� ����Ͻ� �� �ִ� ID�Դϴ�.  </p>
      <input type="button" value="�ݱ�" onclick="setid()">
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
