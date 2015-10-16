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
	
	
		<td width="700" height="700" colspan="2" align="center">
			<h2>개인정보 보호정책</h2>
			
  <table>
 <tr>
 	<td>
 
 <div style="overflow:auto; width:600px; height:200px; border-style:solid; border-color:#EBEBEB; ">
 
 
 ㈜지오다노(이하'회사')는 회원 개개인의 프라이버시를 
 적극적이고 효과적으로 보호하며 
'정보통신망이용촉진 및 정보보호 등에 관한법률' 
등 모든 관련법규를 준수하기 위하여
회원 개인정보취급(처리)방침]을 제정하고 이를 준수하고 있습니다.
회원 가입시 그리고 사이트 방문시에 수시로 확인하여 주시기 바랍니다.<br /><br />

회원 개인정보취급(처리)방침 은 관련 법률 및 지침의 변경과
'회사' 내부 운영방침의 변경에 따라 변경될 수 있습니다.
회원 개인정보취급(처리)방침] 이 변경될 경우 ㈜지오다노
 사이트 서비스 사이트에 게시됩니다.<br /><br />
변경사항에 의문이 있으실 경우는 언제라도 담당자에게 문의하시거나
회사 사이트 및 서비스 사이트를 방문하여 확인하실 수 있습니다. 

</div> 
 	</td>
 
 </tr>
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

   
    