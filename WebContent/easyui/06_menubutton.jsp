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

<a  data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>

<div id="mm">
	<div data-options="iconCls:'icon-edit'">aa</div>
	<div data-options="iconCls:'icon-help'" class="menu-sep">bb</div>
	<div data-options="iconCls:'icon-error'">cc</div>
</div>
	<script type="text/javascript"></script>
</body>
</html>