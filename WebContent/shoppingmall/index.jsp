<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="styles.css">
   <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="script.js"></script>
   <script lang="javascript"></script>
<title>Insert title here</title>
<script lang="javascript">

function goProductListPage(c_no) {
	var objForm = document.category_menu;

	objForm.c_no.value = c_no;

	if(c_no == 'all') {
		objForm.action = "/shoppingmall/shoppingmall/templeteProduct_all.jsp";
	} if(c_no == '0') {
		objForm.action = "/shoppingmall/shoppingmall/templeteProduct_top.jsp";		
	} if(c_no == '1') {
		objForm.action = "/shoppingmall/shoppingmall/templeteProduct_bottom.jsp";	
	}

	objForm.submit();
}
</script>
</head>
<body>
<form name="category_menu" action="" method="post">
	<input type="hidden" name="c_no" value="" />
</form>

<div id='cssmenu'>
<ul>
  <li class="active has-sub">
	<li><a href="#" onclick="javascript:goProductListPage('all');" ><span>Products(전체)</span></a></li>
   <li><a href="#" onclick="javascript:goProductListPage('0');" ><span>Products(상의)</span></a></li>
   <li><a href="#" onclick="javascript:goProductListPage('1');" ><span>Products(하의)</span></a></li>
   <li class="last"><a href="#" onclick="javascript:goProductListPage('2');" ><span>Products(내의)</span></a></li>
</ul>
</div>

</body>
</html>