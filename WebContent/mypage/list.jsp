<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "qna.board.QnaDataBean"%>
<%@ page import = "qna.board.QnaDBBean"%>
<%@ page import = "java.util.List" %> 

<%!
	int pageSize = 10;
%>

<%
	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){
		pageNum = "1";
	}
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage - 1) * pageSize + 1;
	int endRow = currentPage * pageSize;
	int count = 0;
	int num = 0;
	
	List qnaboardList = null;
	QnaDBBean dbPro = QnaDBBean.getInstance();
	count = dbPro.getQnaboardCount();
	if(count>0){
		qnaboardList = dbPro.getQnaboards(startRow,endRow);
	}
	num = count-(currentPage-1)*pageSize;
	
%>
<html>
<head>
<title>1:1문의사항</title>

</head>
<body>
<center><b>글목록(전체글:<%=count %>)</b>
<table width="700">
<tr>
	<td align="right">
	<%if(session.getAttribute("memId")==null){ %>
		<a href="/jsp/member/main.jsp">로그인</a>
		<%}else{ %>
		<a href ="writerForm.jsp">글쓰기</a>
		<%} %>
		</td>
</table>
<%
	if(count == 0){
%>
<table width="700" border="1">
<tr>
	<td align ="center">
	게시판에 저장된 글이 없습니다.
	</td>
</table>
<%}else{ %>
<table border="1" width="700" align="center">
	<tr height="30" >
		<td align="center" width="50">글번호</td>
		<td align="center" width="50">고객번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="500">내용</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="250">전화번호</td>
		<td align="center" width="250">이메일</td>
	</tr>
	<%
	for(int i=0; i<qnaboardList.size(); i++){
		QnaDataBean qnaboard =(QnaDataBean)qnaboardList.get(i);
		
	%>
	<tr height="30">
	<td align="center" width="50"><%=num-- %></td>
	<td width="250">
	<%
		int wid=0;
	if(qnaboard.getQ_level()>0){
		wid=5*(qnaboard.getQ_level());
	%>
	  <img src="images/level.gif" width="<%=wid%>" height="16">
	  <img src="images/re.gif">
	<%}else{%>
	  <img src="images/level.gif" width="<%=wid%>" height="16">
	<%}%>
	<a href="content.jsp?q_no=<%=qnaboard.getQ_no() %>&pageNum=<%=currentPage %>">
	<%=qnaboard.getQ_title() %></a>
	<%{ %>
	<td align="center" width="100">
		<a href="mailto:<%=qnaboard.getQ_email() %>"><%=qnaboard.getQ_writer() %></a></td>
	
	</tr>
	<%} %>
</table>
<%} %>

<%
    if (count > 0) {
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 
        int startPage = (int)(currentPage/10)*10+1;
		int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) endPage = pageCount;
        
        if (startPage > 10) {    %>
        <a href="list.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
<%      }
        for (int i = startPage ; i <= endPage ; i++) {  %>
        <a href="list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
        }
        if (endPage < pageCount) {  %>
        <a href="list.jsp?pageNum=<%= startPage + 10 %>">[다음]</a>
        
<%
        }
    }
}
%>
</center>
</body>
</html>