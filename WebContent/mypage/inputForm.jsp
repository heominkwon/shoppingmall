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
			<li><a class="menuLink" href="\jsp\shoppingmall\intro.jsp">ȸ��Ұ�</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\notice.jsp">��������</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\category.jsp">ī�װ�</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\order.jsp">�ֹ����</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\qnaboard\QnA.jsp">QnA</a></li>
			<li>|</li>
			<li><a class="menuLink" href="\jsp\shoppingmall\event\event.jsp">�̺�Ʈ</a></li>
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
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���̵� :&nbsp;<input type="text" name="M_ID" size="15" maxlength="10">
                   &nbsp;�н�����: &nbsp;<input type="password" name="M_PW" size="15" maxlength="12">
              
            <input type="submit" name="Submit" value="�α���">
            <input type="button"  value="ȸ������" 
            onclick="javascript:window.location='\jsp\join\inputForm.jsp'">
       </form></td>
     <%}else{%>
             <td align="left">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("memId")%>���� �湮�ϼ̽��ϴ�
             	<form  method="post" action="logout.jsp">
             	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="�α׾ƿ�" >
           		 <input type="button" value="ȸ����������" onclick="javascript:window.location='modify.jsp'">
				</form></td><br>
 <%}
 }catch(NullPointerException e){}
 //-----------------------------------�������� ���� ���� ��----------------
 %>
	</tr>
	
	 <tr>
	
		<td height="700" colspan="2" align="center">
			
			
			<script language="JavaScript">
	function checkIt() {
		var userinput = eval("document.userinput");
		if(!userinput.name.value) {
            alert("����� �̸��� �Է��ϼ���");
            return false;
        }
		if(!userinput.id.value) {
            alert("ID�� �Է��ϼ���");
            return false;
        }
		if(!userinput.passwd.value ) {
            alert("��й�ȣ�� �Է��ϼ���");
            return false;
        }
		if(userinput.passwd.value != userinput.passwd2.value)	{
			alert("��й�ȣ�� �����ϰ� �Է��ϼ���.");
			return false;
		}
		if(!userinput.birth.value) {
			alert("��������� �Է����ּ���.");
			return false;
		}
		if(!userinput.address.value){
			alert("�ּҸ� �Է����ּ���.");
			return false;
		}
		if(!userinput.phone.value){
			alert("��ȭ��ȣ�� �Է����ּ���.");
			return false;
		}
	}
	
	function openConfirmid(userinput){
		
		if(userinput.id.value == ""){
			alert("���̵� �Է��ϼ���.");
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
				ȸ������
			</td>
		</tr>
		<tr>
			<td>�̸� �Է�</td>
			<td align="left">
				<input type="text" name="name" maxlength="10">
			</td>
		</tr>
		<tr>
			<td>���̵�</td>
			<td align="left">
				<input type="text" name="id" maxlength="10">
				<input type="button" name="confirm_id" value="���̵� �ߺ�Ȯ��" OnClick="openConfirmid(this.form)">
			</td>
		</tr>
		<tr>
			<td>��й�ȣ</td>
			<td align="left">
				<input type="password" name="passwd" maxlength="12">
			</td>
		</tr>
		<tr>
			<td>��й�ȣ Ȯ��</td>
			<td align="left">
				<input type="password" name="passwd2" maxlength="12">
			</td>
		</tr>
		<tr>
			<td>�������</td>
			<td align="left">
				<input type="text" name="birth" maxlength="8">
			</td>
		</tr>
		<tr>
			<td>�ּ�</td>
			<td align="left">
				<input type="text" name="address" maxlength="50">
			</td>
		</tr>
		<tr>
			<td>��ȭ��ȣ</td>
			<td align="left">
				<input type="text" name="phone" maxlength="13">
			</td>
		</tr>
		<tr>
			<td>�̸���</td>
			<td align="left">
				<input type="text" name="email" maxlength="30">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" >
				<input type="submit" name="confirm" value="���Խ�û">
				<input type="button" value="�������" onclick="javascript:window.location='/jsp/shoppingmall/template.jsp'">
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

   
    