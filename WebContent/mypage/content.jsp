<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "qna.board.QnaDBBean" %>
<%@ page import = "qna.board.QnaDataBean" %>     
<html>
<head>
<title>�Խ���</title>
</head>
<%
	int q_no=Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	try{
		QnaDBBean dbPro = QnaDBBean.getInstance();
		QnaDataBean qnaboard = dbPro.getQnaboard(q_no);
		
		int ref = qnaboard.getRef();
		int q_step=qnaboard.getQ_step();
		int q_level=qnaboard.getQ_level();
%>
<center><b>�۳��뺸��</b>
<br>
<form>
<table width="500" border="1" align="center">
<tr height="30">
	<td align="center" width="125">�۹�ȣ</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_no() %></td>
	<td align="center" width="125">����ȣ</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_mid() %></td>
</tr>
<tr height="30">
	<td align="center" width="125">����</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_title() %></td>
	<td align="center" width="125">�ۼ���</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_writer() %></td>
</tr>
<tr height="30">
	<td align="center" width="125">��ȭ��ȣ</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_phone() %></td>
	<td align="center" width="125">�̸���</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_email() %></td>
</tr>
 <tr>
    <td align="center" width="125" >����</td>
    <td align="left" width="375" colspan="3"><pre><%=qnaboard.getQ_content()%></pre></td>
  </tr>
  <tr height="30">      
    <td colspan="4" align="right" > 
	  <input type="button" value="�ۼ���" 
       onclick="document.location.href='updateForm.jsp?q_no=<%=qnaboard.getQ_no()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="button" value="�ۻ���" 
       onclick="document.location.href='deleteForm.jsp?q_no=<%=qnaboard.getQ_no()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="��۾���" 
       onclick="document.location.href='writeForm.jsp?q_no=<%=q_no%>&ref=<%=ref%>&q_step=<%=q_step%>&q_level=<%=q_level%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="�۸��" 
       onclick="document.location.href='list1.jsp?pageNum=<%=pageNum%>'">
    </td>
  </tr>
</table>    
<%
 }catch(Exception e){} 
 %>

</form>      
</body>
</html>    
















