<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="qna.board.QnaDBBean"%>
<%@ page import="qna.board.QnaDataBean" %>
<%request.setCharacterEncoding("euc-kr"); %> 

<jsp:useBean id="qnaboard" scope="page" class="qna.board.QnaDataBean" >
	<jsp:setProperty name="qnaboard" property="*"/>	
</jsp:useBean>

<%
	System.out.println(qnaboard.getQ_content());
	QnaDBBean dbPro = QnaDBBean.getInstance();	
	dbPro.insertQnaboard(qnaboard);	

	response.sendRedirect("list1.jsp");
%>