<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>easyui_test</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body class="easyui-layout">   
    <div data-options="region:'north',title:'bos管理系统'" style="height:100px;"></div>   
    <div data-options="region:'south'" style="height:100px;"></div>   
    <div data-options="region:'east',iconCls:'icon-reload',title:'East'" style="width:100px;"></div>   
    <div data-options="region:'west',title:'系统面板',split:true" style="width:200px;">
    	<div class="easyui-accordion" data-options="fit:true">
    		<div title="一"></div>
    		<div title="二">
    			<ul id="ztree1" class="ztree"></ul>
    			<script type="text/javascript">
    				$(function() {
						var setting = {
								data :{
									simpleData:{
										enable:true
									}
								},
								callback:{
									onClick:function(a,b,treeNode){
										
										var page = treeNode.page
										if( page != undefined){
											var flag = $("#tt").tabs("exists",treeNode.name)
											if(flag){
												$("#tt").tabs("select",treeNode.name)
											}else{
												$("#tt")
												.tabs(
														"add",
														{
															title:treeNode.name,
															content:'<iframe frameborder="0" width="100%" height="100%" src="'+page+'"></iframe>',
															closable:true,
															iconCls:'icon-edit'
														}
														);
											}
											
										}
									}
									
								}
						}
						
						var zNodes = [
						              {id:'1',pId:'0',name:'系统管理'},
						              {id:'2',pId:'0',name:'用户管理'},
						              {id:'21',pId:'2',name:'用户添加'},
						              {id:'3',pId:'0',name:'权限管理'}
						              ];
						var url = "${pageContext.request.contextPath}/json/menu.json";
						$.post(url,{},function(data){
							$.fn.zTree.init($("#ztree1"),setting,data);
						},'json');
						
						
					});
    			</script>
    		</div>
    		<div title="三"></div>
    	</div>
    </div>   
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    	<div id="tt" class="easyui-tabs" data-options="fit:true">
    		<div title="一" data-options="closable:true,iconCls:'icon-add'"></div>
    		<div title="二"></div>
    		<div title="三"></div>
    	</div>
    </div>   
</body>  

</body>
</html>