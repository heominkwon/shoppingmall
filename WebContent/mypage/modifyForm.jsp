<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ page import = "project1.logon.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>shop</title>
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>

<body>
<table width="500" height="500" border="0" align="center">
	<tr>
		<td height="100" align="center" colspan="2">
			<jsp:include page="/shoppingmall/top.jsp"/>
		</td>
	</tr>
	<tr>
	  <td height="100" align="center">
	   <style>
                #topMenu {
                        height: 40px;
                        width: 1170px;
                }

                #topMenu ul li {
                        list-style: none;
                        color: white;
                        background-color: #2d2d2d;
                        float: left;
                        line-height: 40px;
                        vertical-align: middle;
                        text-align: center;
                }

                #topMenu .menuLink {
                        text-decoration:none;
                        color: white;
                        display: block;
                        width: 150px;
                        font-size: 12px;
                        font-weight: bold;
                        font-family: "Trebuchet MS";
                }
                #topMenu .menuLink:hover {
                        color: white;
                        background-color: #4d4d4d;
                }

    </style>
	
	<nav id="topMenu" >
	
		<ul>
			
			<li><a class="menuLink" href="/jsp/shoppingmall/template.jsp">HOME</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/intro.jsp">회사소개</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/notice.jsp">공지사항</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/category.jsp">카테고리</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/order.jsp">주문배송</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/qnaboard/list1.jsp">QnA</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/event/event.jsp">이벤트</a></li>
		</ul>
	</nav>
    </td>
    
    

	</tr>
	
	<tr>
	 <%
//------------------------------------------영욱이형 변경 내용----------------------------------
 try{
   if(session.getAttribute("memId")==null){%>
<script language="javascript">

function focusIt()
{      
   
   document.inform.id.focus();
}

function checkIt()
 {
   inputForm=eval("document.inform");
   if(!inputForm.id.value){
     alert("아이디를 입력하세요..");
	 inputForm.id.focus();
	 return false;
   }
   if(!inputForm.passwd.value){
     alert("패스워드를 입력하세요..");
	 inputForm.passwd.focus();
	 return false;
   }
 }

</script>
</head>

<body onLoad="focusIt();" >
<td align="left">   
       <form name="inform" method="post" action="loginPro.jsp"  onSubmit="return checkIt();">
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디:&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
                   &nbsp;패스워드: &nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
              
            <input type="submit" name="Submit" value="로그인">
            <input type="button"  value="회원가입" 
            onclick="javascript:window.location='/jsp/join/inputForm.jsp'">
       </form></td>
     <%}else{%>
             <td align="left">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>님이 방문하셨습니다
             	<form  method="post" action="/jsp/join/logout.jsp">
             	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="로그아웃" >
           		 <input type="button" value="회원정보변경" onclick="javascript:window.location='/jsp/join/modifyForm.jsp'">
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------영욱이형 변경 내용 끝----------------
 %>
	</tr>
	
	 <tr>
	
		<td height="700" colspan="2" align="center" valign="top" ><br /><br /><br /><br />
			
<%
	String id = (String)session.getAttribute("memId");
	LogonDBBean manager = LogonDBBean.getInstance();
	LogonDataBean c = manager.getMember(id);
	
	try{
%>

	<form method="post" action= "modifyPro.jsp" name="userinput" onsubmit="return checkIt()">

	<table width="400" height="200" border="1" cellspacing="0" align="center">
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
				<input type="hidden" name="name" value="<%=c.getName() %>" size="20">
			</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>
			<%=c.getId() %>
				<input type="hidden" name="id" value="<%=c.getId() %>" size="20">				
			</td>  
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="passwd" maxlength="12" size="20">
			</td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td>
				<input type="password" name="passwd2" maxlength="12" size="20">
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
			    <input type="text" name="birth" maxlength="6" value="<%=c.getBirth()%>" size="20">
			   
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
			 
			    <input type="text" name="address" maxlength="50" value="<%=c.getAddress()%>" size="20">
				
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
			
			    <input type="text" name="phone" maxlength="13" value="<%=c.getPhone()%>" size="20">
			
			
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>
			  <%if(c.getEmail()==null){%>
				<input type="text" name="email" maxlength="30" size="20">
			  <%}else{ %>
			    <input type="text" name="email" maxlength="30" value="<%=c.getEmail()%>" size="20">
			  <%} %>
			</td>
		</tr>
		
	</table><br />

				<input type="submit" name="modify" value="정보수정"> &nbsp;
		
				<input type="button" value="취소" onclick="javascript:window.location='/jsp/join/mypage.jsp'">
		
	</form>

<%}catch(Exception e){}%>	
			
			

		</td>
		
	</tr>
	
	
	</tr>
	
</table>
<center>
<tr>

		<td height="50" colspan="2" align="center">
			<jsp:include page="/shoppingmall/bottom.jsp"/>		
		</td>
</tr>
</center>
</body>
</html>

   
    