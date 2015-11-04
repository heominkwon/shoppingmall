<%@ page contentType="text/html; charset=EUC-KR"%>
    
<%
	int q_no=0, q_ref=1, q_step=0, q_level=0;
	try{
		if(request.getParameter("q_no")!=null){
		q_no=Integer.parseInt(request.getParameter("q_no"));
		q_step=Integer.parseInt(request.getParameter("q_step"));
		q_level=Integer.parseInt(request.getParameter("q_level"));	
		}
%>

<center><b>글쓰기</b>
<br>
<form mthod="post" name="writerform" action="writerPro.jsp">
<input type="hidden" name="q_no" value="<%=q_no %>">
<input type="hidden" name="q_step" value="<%=q_step %>">
<input type="hidden" name="q_level" value="<%=q_level %>">
<table border="1">
	<tr>
		<td align="right" colspan="2">
			<a href = "list1.jsp"> 글목록</a>
		
		</td>
		
	</tr>
	<tr>
		<td align="center">고객번호</td>
		<td><input type="text" name="q_mid">
		</td>		
	</tr>
	<tr>
		<td align="center">제 목</td>
		<%if(request.getParameter("q_title")==null){ %>
			<td><input type="text" name="q_title"></td>
		<%}else{ %>
			<td><input type="text" name="q_title" value="답변"></td>
		<%} %>
	</tr>
	<tr>
    <td  align="center" >내 용</td>
   
     <td><textarea name="q_content" rows="13" cols="40"></textarea></td>
  </tr>
	<tr>
	<td align="center">작성자</td>
		<td><input type="text" name="q_writer"></td>
	</tr>
	
	<tr>
	<td align="center">전화번호</td>
		<td><input type="text" name="q_phone"></td>
	</tr>
	
	<tr>
	<td align="center">이메일</td>
		<td><input type="text" name="q_email"></td>
	</tr>
	<tr>
	<td colspan="2" align="center">
		<input type="submit" value="글쓰기">
		<input type="reset" value="다시작성">
		<input type="button" value="목록보기" onclick="window.location='list1.jsp'">
		</td></tr></table>
		<%
	}catch(Exception e){}
		%>
</form>

</center>