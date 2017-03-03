<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>message</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<body >

	<script type="text/javascript">   
	$.messager.progress();
	window.setTimeout(function(){
	$.messager.progress('close');
	$.messager.show({
		title:'登录提示',
		msg:'欢迎XX登陆！',
		timeout:3000
	});
	},3000);
/*  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	        alert('确认删除');    
	    }    
	});    */

	</script>
</body>
</html>