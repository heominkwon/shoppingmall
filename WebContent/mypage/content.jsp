<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "qna.board.QnaDBBean" %>
<%@ page import = "qna.board.QnaDataBean" %>     
<html>
<head>
<title>게시판</title>
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
<center><b>글내용보기</b>
<br>
<form>
<table width="500" border="1" align="center">
<tr height="30">
	<td align="center" width="125">글번호</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_no() %></td>
	<td align="center" width="125">고객번호</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_mid() %></td>
</tr>
<tr height="30">
	<td align="center" width="125">제목</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_title() %></td>
	<td align="center" width="125">작성자</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_writer() %></td>
</tr>
<tr height="30">
	<td align="center" width="125">전화번호</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_phone() %></td>
	<td align="center" width="125">이메일</td>
	<td align="center" width="125" >
		<%=qnaboard.getQ_email() %></td>
</tr>
 <tr>
    <td align="center" width="125" >내용</td>
    <td align="left" width="375" colspan="3"><pre><%=qnaboard.getQ_content()%></pre></td>
  </tr>
  <tr height="30">      
    <td colspan="4" align="right" > 
	  <input type="button" value="글수정" 
       onclick="document.location.href='updateForm.jsp?q_no=<%=qnaboard.getQ_no()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="button" value="글삭제" 
       onclick="document.location.href='deleteForm.jsp?q_no=<%=qnaboard.getQ_no()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="답글쓰기" 
       onclick="document.location.href='writeForm.jsp?q_no=<%=q_no%>&ref=<%=ref%>&q_step=<%=q_step%>&q_level=<%=q_level%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="글목록" 
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
















