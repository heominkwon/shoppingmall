<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<html>
<head>

<title>ȸ��Ż��</title>

	<script language="javascript">
	
	
	</script>
</head>
<body>
<form name="myform" action="delPro.jsp" method="post" onSubmit="return checkIt()">
<table cellSpacing=1 cellPadding=1 align="center">
	<tr>
		<td colspan="2" align="middle">
			<font size="+1"><b>ȸ��Ż��</b></font>
		</td>
	</tr>
	<tr>
		<td align= center>��й�ȣ</td>
		<td align= center>
			<input type=password name="passwd" maxlength="12">
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type=submit value="ȸ��Ż��">
			<input type="button" value="���" onclick="javascript:window.location='modifyForm.jsp'">
		</td>
	</tr>
	

</table>
</form>
</body>
</html>