<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page import = "project1.logon.LogonDBBean" %>
<%@ page import = "project1.logon.LogonDataBean" %>
<%@ page import = "Connection.ConnectionDAO" %>
<%@ page import = "Connection.ConnectionDTO" %>
<%@ page import = "java.sql.Timestamp" %>
 
<% request.setCharacterEncoding("euc-kr"); %>
 
<%
	String id = request.getParameter("M_ID");	
	String passwd = request.getParameter("M_PW");
	ConnectionDAO dbpro = ConnectionDAO.getInstance();
	ConnectionDTO con=null;
	LogonDBBean manager = LogonDBBean.getInstance();
	
	int check = manager.userCheck(id,passwd);
	if(check==1){ 
		int m_no = manager.getUserNo(id);
		session.setAttribute("memId",id);
		session.setAttribute("m_no",m_no);		
		response.sendRedirect("main.jsp");
	}else if(check==0){%>
	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
<% }else{ %>
	<script>
		alert("아이디가 맞지 않습니다.");
		history.go(-1);
	</script>	
<%} 
	String current=new Timestamp(System.currentTimeMillis()).toString();
	int year=Integer.parseInt(current.substring(0,4));
	int month=Integer.parseInt(current.substring(5,7));
	
	int checkcon=dbpro.checkConnection(year, month);		
	
	
	if(checkcon==0){
		LogonDataBean person=manager.getMember(id);
		int age=1000000+((year-2000)*10000)-person.getBirth();		
		dbpro.insertConnection(year,month);
		dbpro.updateConnection(year, month, age/10000);
	}else{
		
		LogonDataBean person=manager.getMember(id);
		int age=1000000+((year-2000)*10000)-person.getBirth();		
		dbpro.updateConnection(year, month, age/10000);
		
				
		
	}

%>