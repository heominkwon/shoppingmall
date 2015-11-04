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
<table width="1000" height="100" border="0" align="center">
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
			<li><a class="menuLink" href="\jsp\qnaboard\QnA.jsp">QnA</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\event\event.jsp">이벤트</a></li>
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
            onclick="javascript:window.location='\jsp\join\inputForm.jsp'">
       </form></td>
     <%}else{%>
             <td align="left">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>님이 방문하셨습니다
             	<form  method="post" action="logout.jsp">
             	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="로그아웃" >
           		 <input type="button" value="회원정보변경" onclick="javascript:window.location='modify.jsp'">
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------영욱이형 변경 내용 끝----------------
 %>
	</tr>
	
	 <tr>
	
		<td height="700" colspan="2" align="center">
			
			
			<script language="JavaScript">
	function checkIt() {
		var userinput = eval("document.userinput");
		if(!userinput.name.value) {
            alert("사용자 이름을 입력하세요");
            return false;
        }
		if(!userinput.id.value) {
            alert("ID를 입력하세요");
            return false;
        }
		if(!userinput.passwd.value ) {
            alert("비밀번호를 입력하세요");
            return false;
        }
		if(userinput.passwd.value != userinput.passwd2.value)	{
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
		if(!userinput.birth.value) {
			alert("생년월일을 입력해주세요.");
			return false;
		}
		if(!userinput.address.value){
			alert("주소를 입력해주세요.");
			return false;
		}
		if(!userinput.phone.value){
			alert("전화번호를 입력해주세요.");
			return false;
		}
	}
	
	function openConfirmid(userinput){
		
		if(userinput.id.value == ""){
			alert("아이디를 입력하세요.");
			return;
		}
		url="confirmId.jsp?id=" + userinput.id.value;
		open(url,"confirm","toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no, width=300,height=200");
	}
</script>


<form method="post" action="/jsp/join/inputPro.jsp" name="userinput" onSubmit="return checkIt()">
	<table width="450" height="50" border="1" align="center" cellpadding="10">
		<tr>
			<td colspan="2" align="center">
				회원가입
			</td>
		</tr>
		<tr>
			<td>이름 입력</td>
			<td align="left">
				<input type="text" name="name" maxlength="10">
			</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td align="left">
				<input type="text" name="id" maxlength="10">
				<input type="button" name="confirm_id" value="아이디 중복확인" OnClick="openConfirmid(this.form)">
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td align="left">
				<input type="password" name="passwd" maxlength="12">
			</td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td align="left">
				<input type="password" name="passwd2" maxlength="12">
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td align="left">
				<input type="text" name="birth" maxlength="8">
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td align="left">
				<input type="text" name="address" maxlength="50">
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td align="left">
				<input type="text" name="phone" maxlength="13">
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td align="left">
				<input type="text" name="email" maxlength="30">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" >
				<input type="submit" name="confirm" value="가입신청">
				<input type="button" value="가입취소" onclick="javascript:window.location='/jsp/shoppingmall/template.jsp'">
			</td>
		</tr>	
	
	
	
	</table>
			
			
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

   
    