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
			<li><a class="menuLink" href="\jsp\shoppingmall\intro.jsp">ȸ��Ұ�</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\notice.jsp">��������</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\category.jsp">ī�װ�</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\order.jsp">�ֹ����</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\qnaboard\list1.jsp">QnA</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\customerCenter.jsp">������</a></li>
		</ul>
	</nav>
    </td>
    
    

	</tr>
	
	<tr>
	 <%
	//------------------------------------------�������� ���� ����----------------------------------
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
	     alert("���̵� �Է��ϼ���..");
		 inputForm.M_ID.focus();
		 return false;
	   }
	   if(!inputForm.M_PW.value){
	     alert("�н����带 �Է��ϼ���..");
		 inputForm.M_PW.focus();
		 return false;
	   }
	 }

	</script>
	</head>

	<body onLoad="focusIt();" >
	<td align="left">   
	       <form name="inform" method="post" action="/jsp/join/loginPro.jsp"  onSubmit="return checkIt();">
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���̵�:&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
			&nbsp; �н�����:&nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
	              
	          <input type="submit" name="Submit" value="�α���">
	            <input type="button"  value="ȸ������" 
	            onclick="javascript:window.location='/jsp/join/inputForm.jsp'">
	       </form></td>
	     <%}else{%>
	             <td align="left">
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>���� �湮�ϼ̽��ϴ�
	             	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form  method="post" action="/jsp/join/logout.jsp">
	             	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="�α׾ƿ�" >
	             	 <%if(session.getAttribute("memId").equals("admin")){%>
	             	 <a class="menuLink" href="/jsp/admin/admain.jsp">������������</a>
	             	 <%}else{ %>
	           		 <a class="menuLink" href="/jsp/join/mypage.jsp">����������</a>
	           		 <%} %>
					</form></td><br>
	 <%}
	 }catch(NullPointerException e){}
	 //-----------------------------------�������� ���� ���� ��----------------
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

    