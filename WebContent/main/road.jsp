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
<table width="1000" height="1" border="0" align="center">
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
			
			<li><a class="menuLink" href="template.jsp">HOME</a></li>
			<li>|</li>
			<li><a class="menuLink" href="intro.jsp">회사소개</a></li>
			<li>|</li>
			<li><a class="menuLink" href="notice.jsp">공지사항</a></li>
			<li>|</li>
			<li><a class="menuLink" href="category.jsp">카테고리</a></li>
			<li>|</li>
			<li><a class="menuLink" href="order.jsp">주문배송</a></li>
			<li>|</li>
			<li><a class="menuLink" href="QnA.jsp">QnA</a></li>
			<li>|</li>
			<li><a class="menuLink" href="customerCenter.jsp">고객센터</a></li>
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
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디 :&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
                   &nbsp;패스워드: &nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
              
            <input type="submit" name="Submit" value="로그인">
            <input type="button"  value="회원가입" 
            onclick="javascript:window.location='inputForm.jsp'">
       </form></td>
     <%}else{%>
             <td align="left">
             <%=session.getAttribute("memId")%>님이 방문하셨습니다
             	<form  method="post" action="logout.jsp">
             	 <input type="submit"  value="로그아웃" >
           		 <input type="button" value="회원정보변경" onclick="javascript:window.location='modify.jsp'">
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------영욱이형 변경 내용 끝----------------
 %>
	</tr>
	
	 <tr>
	
		 <td height="700" colspan="2" align="center"> 
	
				
	<table cellpadding="0" cellspacing="100" width="1000" align="left" border=0  > <tr> 
	<td style="border:1px solid #cecece;"><a href="http://map.naver.com/?menu=location&mapMode
		=0&lat=37.4990176&lng=127.0328555&dlevel=12&searchCoord=127.0333571%3B37.4954844&query=
		7ISc7Jq47Yq567OE7IucIOqwleuCqOq1rCDsl63sgrzrj5kgODIzLTI0IOuCqOuPhOu5jOuUqQ%3D%3D&mpx=
		09680640%3A37.4954844%2C127.0333571%3AZ11%3A0.0218492%2C0.0097926&tab=1&enc=b64" 
		target="_blank"><img src="http://prt.map.naver.com/mashupmap/print?key=p1444880579335_
		187422789" width="400" height="340" alt="지도 크게 보기" title="지도 크게 보기" border="0" 
		style="vertical-align:top;" align=left/></a></td> <td><table width=400 align=center board=1>
		<tr><td><font size="5" face="휴먼매직체"><strong>찾아오시는길</strong><br/><br/><br/>
		서울특별시 강남구 역삼동 823-24 남도빌딩 2층, 3층 (T: 1544-9970 / F: 070-8290-2889)</font></td></tr>
		</table></td> </tr>    <tr> <td> <table cellpadding="0" 
		cellspacing="0" width="100%"  > </td>  </tr>   </table> </td> </tr> 
		 		
		   </table>
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

   
    