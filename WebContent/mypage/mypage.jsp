<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<% request.setCharacterEncoding("euc-kr"); %>

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
             <%=session.getAttribute("memId")%>님이 방문하셨습니다
             	<form  method="post" action="logout.jsp">
             	 <input type="submit"  value="로그아웃" >
           		 
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------영욱이형 변경 내용 끝----------------
 %>
	</tr>
	
	 <tr>
	
		 <td height="700" colspan="2" align="center">
			
			<script language="JavaScript">
<!--
if (document.getElementById){
    document.write('<style type="text/css">\n')
    document.write('.submenu{display: none;}\n')
    document.write('</style>\n')
}

function SwitchMenu(obj){
    if(document.getElementById){
    var el = document.getElementById(obj);
    var ar = document.getElementById("maindiv").getElementsByTagName("span"); 
        if(el.style.display != "block"){
            for (var i=0; i<ar.length; i++){
                if (ar[i].className=="submenu") 
                ar[i].style.display = "none";
            }
            el.style.display = "block";
        }else{
            el.style.display = "none";
        }
    }
}
//-->
</script>

<style type="text/css">
a{text-decoration:none;font-weight:bold:color:#ffffff:}
#maindiv{float:left; margin:30px; width:150px; height:500px; background-color:#66CCFF; text-align:center;}

</style>
<body>

<div id="maindiv">
<p> <div onClick="SwitchMenu('sub1')" onmouseover="this.style.cursor='hand'"><B><font color="white">주문/배송</font></B></div>
    <span class="submenu" id="sub1">
        - <a href="new_order.jsp" target="ppp"><font color="white">주문내역/구매확정</font></a><br/><br/>     
        - <a href="cancle_order.jsp" target="ppp"><font color="white">주문교환/반품</font></a><br/><br/> 
        - <a href="changeAddress.jsp" target="ppp"><font color="white">배송지/주문내용변경</font></a><br/><br/>      
    </span>

<p> <div onClick="SwitchMenu('sub3')" onmouseover="this.style.cursor='hand'"><B><font color="white">나의정보</font></B></div>
    <span class="submenu" id="sub3">
        - <a href="modifyForm.jsp" target="ppp"><font color="white">회원정보관리</font></a><br/><br/>
        - <a href="deleteForm.jsp" target="ppp"><font color="white">회원탈퇴</font></a><br/><br/>
      
    </span>
</div>

 	<div>
	<iframe src="result.jsp" width="700" height="800" name="ppp" frameborder="0" ></iframe>
	</div> 
				
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

   
    
