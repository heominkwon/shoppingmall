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
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디:&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
                   &nbsp;패스워드: &nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
              
            <input type="submit" name="Submit" value="로그인">
            <input type="button"  value="회원가입" 
            onclick="javascript:window.location='/jsp/join/inputForm.jsp'">
       </form></td>
     <%}else{%>
             <td align="left">
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>님이 방문하셨습니다
             <form  method="post" action="/jsp/join/logout.jsp">
             	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="로그아웃" >
           		 <input type="button" value="회원정보변경" onclick="javascript:window.location='/jsp/join/modify.jsp'">
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------영욱이형 변경 내용 끝----------------
 %>
	</tr>
	
	 <tr>
	
	<td height="700" colspan="2" align="center">
	
	<table width="500" height="500" align="center" text border="0">
 <tr>
 	<h2>shopping guide</h2>
 	<td>
 	저희 쇼핑몰을 방문해 주셔서 감사드립니다. 저희 쇼핑몰은 회원제를 실시하고 있습니다. 
	처음 오신 분은 먼저 <a href="/jsp/join/inputForm.jsp" style="text-decoration:none"> 회원가입</a>을 하신 후 이용하시길 바랍니다.
	<br /><br /><br />

	<strong>상품 주문방법</strong><br /><br />
 	1. 각 코너를 클릭하셔서 들어갑니다.<br />
	2. "바로가기" 메뉴 또는 사진이나 상품명을 클릭하세요!<br />
	3. "장바구니 담기"를 클릭하세요!<br />
	4. "장바구니에 넣었습니다" 메시지가 나오면, 주문상품을 확인한 후<br /> 
	 &nbsp; &nbsp;"주문버튼"을 클릭하세요!<br />
	5. 주문버튼을 누르면, "주문서"가 나옵니다! 주문서를 작성 후 "주문"을 
	 &nbsp; &nbsp;클릭하면 주문이 완료됩니다! 
 <br /><br />
 	배송일배송 방법은 택배입니다.<br />
 	주문하신 날로부터 1 ~ 4일 안에 받을 수 있습니다.<br />
         온라인 입금 시 입금 확인 후 1 ~ 4일<br />
         신용카드 결제 시 주문 후 1 ~ 4일<br />
 <br /><br />
  - 주소 : 서울특별시 강남구 역삼동 823-24 남도빌딩2층<br />
  - 전화 : 1544-9970<br />
  - 메일 : help@khmaster.com<br />
 
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

   
    