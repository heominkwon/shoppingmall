<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

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
<table width="1200" height="100" border="0" align="center">
	<tr>
		<td height="100" align="center" colspan="2">
			<jsp:include page="top.jsp"/>
		</td>
	</tr>
	<tr>
	  <td height="100" align="center">
	   <style>
                #topMenu {
                        height: 40px;
                        width: 1200px;
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
			
			<li><a class="menuLink" href="\jsp\shoppingmall\template.jsp">HOME</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\intro.jsp">회사소개</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\notice.jsp">공지사항</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\category.jsp">카테고리</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\order.jsp">주문배송</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\qnaboard\list1.jsp">QnA</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\customerCenter.jsp">고객센터</a></li>
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
	   if(!inputForm.M_ID.value){
	     alert("아이디를 입력하세요..");
		 inputForm.M_ID.focus();
		 return false;
	   }
	   if(!inputForm.M_PW.value){
	     alert("패스워드를 입력하세요..");
		 inputForm.M_PW.focus();
		 return false;
	   }
	 }

	</script>
	</head>

	<body onLoad="focusIt();" >
	<td align="left">   
	       <form name="inform" method="post" action="/jsp/join/loginPro.jsp"  onSubmit="return checkIt();">
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디:&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
			&nbsp; 패스워드:&nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
	              
	          <input type="submit" name="Submit" value="로그인">
	            <input type="button"  value="회원가입" 
	            onclick="javascript:window.location='/jsp/join/inputForm.jsp'">
	       </form></td>
	     <%}else{%>
	             <td align="left">
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>님이 방문하셨습니다
	             	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form  method="post" action="/jsp/join/logout.jsp">
	             	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="로그아웃" >
	             	 <%if(session.getAttribute("memId").equals("admin")){%>
	             	 <a class="menuLink" href="/jsp/admin/admain.jsp">관리자페이지</a>
	             	 <%}else{ %>
	           		 <a class="menuLink" href="/jsp/join/mypage.jsp">마이페이지</a>
	           		 <%} %>
					</form></td><br>
	 <%}
	 }catch(NullPointerException e){}
	 //-----------------------------------영욱이형 변경 내용 끝----------------
 %>
	</tr>
	
	 <tr>
	
		<td height="600" colspan="2" align="center">
			<jsp:include page="mainpage.jsp"/>
		</td>
		
		
	</tr>
	
	<tr>
		<td height="50" colspan="2" align="center">
			<jsp:include page="bottom.jsp"/>		
		</td>
	</tr>
	
</table>

</body>
</html>

    