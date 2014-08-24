<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<title>用户管理</title>
</head>
<body onload="clearHiddenCheckBox()">
<div class="pageTitle">您所在的位置：系统管理 - 用户管理</div>	
<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
<form action="/admin/user" class="form" method="post" name="searchForm">
     名称: <input type="text" name="name" value="${user.name }" size="10"/>
<input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />
<A href="<c:url value="${ctx}/sys/resc/form"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></A>
<A href="javascript:batch_do('删除资源','<c:url value="${ctx}/sys/resc/del" />');"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></A>
</form>
</div>
	<div id="tableDiv">
	    <ec:table items="users" var="u"
	              action="${ctx}/admin/user"
			xlsFileName="用户列表.xls" 
			csvFileName="用户列表.csv"			
			useAjax="true"
			minColWidth="80"
			doPreload="false"
			pageSizeList="10,20,50,100,500,all" 
			rowsDisplayed="15" 		
			retrieveRowsCallback="process" 		
			sortable="true" 		
			generateScript="true" 		
			resizeColWidth="true" 		
			classic="true" 		
			filterable="flase"	
			width="100%"
			height="420px"
			minHeight="360"
			toolbarContent="navigation|pagejump|pagesize|refresh |export|extend|status"
		>
	        <ec:row>
	            <ec:column property="rowcount" resizeColWidth="false" cell="rowCount" title="序号" sortable="false" width="40px"/>
	            <ec:column property="loginName" title="登录名" width="120px" sortable="true"/>
	            <ec:column property="name" title="名称" width="120px"/>
	            <ec:column property="roles" title="角色" width="200px"/>
	            <ec:column property="registerDate" title="创建时间" width="120px" >
	            	<fmt:formatDate value='${u.registerDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            <ec:column property="edit" title="修改" sortable="false" viewsAllowed="html" width="40px">
	                <A href="<c:url value="/sys/resc/form?id=${u.id}"/>">
	                    <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
	                </A>
	            </ec:column>
	            <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
	                <input type="checkbox" name="itemlist" value="${u.id}" style="border:0px"/>
	            </ec:column>
	        </ec:row>
	    </ec:table>
	    <c:if test="${empty users}">
			<font color="red">无符合条件的查询！</font>
		</c:if>
	    
	</div>
</body>
</html>