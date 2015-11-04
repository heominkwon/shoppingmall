<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<<<<<<< HEAD
=======

<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<% request.setCharacterEncoding("euc-kr"); %>

>>>>>>> origin/master
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<<<<<<< HEAD
<title></title>
</head>
<body>
	<table border="0" width="1500" align="center">
	<tr>
		<td height="100" align="center" colspan="2">
				<jsp:include page = "/shoppingmall/mainTop.jsp" />
		</td>
	</tr>
	<tr>
			<td valign="top">
 				<jsp:include page="index.jsp" flush="false" />
	
			</td>
		<td>
				<jsp:include page = "/shoppingmall/mainpage.jsp" />
		</td>
	</tr>
	<tr>
			<td height="50" colspan="2" align="center">
				<jsp:include page="/shoppingmall/bottom.jsp"/>		
			</td>
	</tr>
	</table>
</body>
</html>
=======
<title>shop</title>
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>

<body>
<table width="1000" height="1" border="1" align="center">
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
			<li><a class="menuLink" href="/jsp/shoppingmall/customerCenter.jsp">고객센터</a></li>
		</ul>
	</nav>
    </td>
    
    

	</tr>
	
	<tr border="1">
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
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디 :&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
                   &nbsp;패스워드: &nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
              
            <input type="submit" name="Submit" value="로그인">
            <input type="button"  value="회원가입" 
            onclick="javascript:window.location='inputForm.jsp'">
       </form></td>
     <%}else{%>
             <td align="left">
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>님이 방문하셨습니다
             	<form  method="post" action="logout.jsp">
             	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="로그아웃" >
           		 <input type="button" value="회원정보변경" onclick="javascript:window.location='modifyForm.jsp'">
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------영욱이형 변경 내용 끝----------------
 %>
	</tr>
	
	 <tr>
	
		 <td height="700" colspan="2" align="left" valign="top">
			
	
<html lang=''>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="styles.css">
   <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="script.js"></script>
  
</head>
<body>

<div id='cssmenu' onmouseover="this.style.cursor='hand'"><br /><br /><br />
<ul>
   <li><a href='/jsp/shoppingmall/template.jsp'><span>HOME</span></a></li>
   
   <li class='active has-sub'><a href='#'><span>주문배송</span></a>  
      <ul>
         <li class='has-sub'><a href='#'><span>주문내역/배송현황</span></a></li>
         <li class='has-sub'><a href='#'><span>주문취소/교환/반품</span></a></li>
          <li class='has-sub'><a href='#'><span>주문내용변경</span></a></li>               
      </ul>
   </li>
   
  
   <li class='active has-sub'><a href='#'><span>서비스정보</span></a>
   
    <ul>
    <li class='has-sub'><a href='#'><span>이벤트</span></a></li>
    </ul>
    </li>
    
   
   <li class='active has-sub'><a href='#'><span>나의정보</span></a>
   
    <ul>
    <li class='has-sub'><a href='#'><span>회원정보변경</span></a></li>
     <li class='has-sub'><a href='#'><span>회원탈퇴</span></a></li>
    </ul>
    </li>
</ul>
</div>

<!-- <div>
	<iframe src="result.jsp" width="800" height="600" name="ppp" frameborder="0" align="left"></iframe>
	</div> 	 -->
				
		
		
</td>

</tr>
	
	
		
	
	
	<tr>
		<td height="50" colspan="2" align="center">
			<jsp:include page="/shoppingmall/bottom.jsp"/>		
		</td>
	</tr>
	
</table>

</body>
</html>

   
    
    
>>>>>>> origin/master
