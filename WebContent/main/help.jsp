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
			<li><a class="menuLink" href="/jsp/shoppingmall/intro.jsp">ȸ��Ұ�</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/notice.jsp">��������</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/category.jsp">ī�װ�</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/order.jsp">�ֹ����</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/qnaboard/list1.jsp">QnA</a></li>
			<li>|</li>
			<li><a class="menuLink" href="/jsp/shoppingmall/customerCenter.jsp">������</a></li>
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
   if(!inputForm.id.value){
     alert("���̵� �Է��ϼ���..");
	 inputForm.id.focus();
	 return false;
   }
   if(!inputForm.passwd.value){
     alert("�н����带 �Է��ϼ���..");
	 inputForm.passwd.focus();
	 return false;
   }
 }

</script>
</head>

<body onLoad="focusIt();" >
<td align="left">   
       <form name="inform" method="post" action="loginPro.jsp"  onSubmit="return checkIt();">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���̵�:&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
                   &nbsp;�н�����: &nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
              
            <input type="submit" name="Submit" value="�α���">
            <input type="button"  value="ȸ������" 
            onclick="javascript:window.location='/jsp/join/inputForm.jsp'">
       </form></td>
     <%}else{%>
             <td align="left">
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>���� �湮�ϼ̽��ϴ�
             <form  method="post" action="/jsp/join/logout.jsp">
             	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="�α׾ƿ�" >
           		 <input type="button" value="ȸ����������" onclick="javascript:window.location='/jsp/join/modify.jsp'">
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------�������� ���� ���� ��----------------
 %>
	</tr>
	
	 <tr>
	
	<td height="700" colspan="2" align="center">
	
	<table width="500" height="500" align="center" text border="0">
 <tr>
 	<h2>shopping guide</h2>
 	<td>
 	���� ���θ��� �湮�� �ּż� ����帳�ϴ�. ���� ���θ��� ȸ������ �ǽ��ϰ� �ֽ��ϴ�. 
	ó�� ���� ���� ���� <a href="/jsp/join/inputForm.jsp" style="text-decoration:none"> ȸ������</a>�� �Ͻ� �� �̿��Ͻñ� �ٶ��ϴ�.
	<br /><br /><br />

	<strong>��ǰ �ֹ����</strong><br /><br />
 	1. �� �ڳʸ� Ŭ���ϼż� ���ϴ�.<br />
	2. "�ٷΰ���" �޴� �Ǵ� �����̳� ��ǰ���� Ŭ���ϼ���!<br />
	3. "��ٱ��� ���"�� Ŭ���ϼ���!<br />
	4. "��ٱ��Ͽ� �־����ϴ�" �޽����� ������, �ֹ���ǰ�� Ȯ���� ��<br /> 
	 &nbsp; &nbsp;"�ֹ���ư"�� Ŭ���ϼ���!<br />
	5. �ֹ���ư�� ������, "�ֹ���"�� ���ɴϴ�! �ֹ����� �ۼ� �� "�ֹ�"�� 
	 &nbsp; &nbsp;Ŭ���ϸ� �ֹ��� �Ϸ�˴ϴ�! 
 <br /><br />
 	����Ϲ�� ����� �ù��Դϴ�.<br />
 	�ֹ��Ͻ� ���κ��� 1 ~ 4�� �ȿ� ���� �� �ֽ��ϴ�.<br />
         �¶��� �Ա� �� �Ա� Ȯ�� �� 1 ~ 4��<br />
         �ſ�ī�� ���� �� �ֹ� �� 1 ~ 4��<br />
 <br /><br />
  - �ּ� : ����Ư���� ������ ���ﵿ 823-24 ��������2��<br />
  - ��ȭ : 1544-9970<br />
  - ���� : help@khmaster.com<br />
 
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

   
    