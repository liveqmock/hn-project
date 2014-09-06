<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<title>会员管理</title>
</head>
<body onload="clearHiddenCheckBox()">
	<div class="pageTitle">您所在的位置：系统管理 - 会员管理</div>	
	<div style="text-align: left;" class="queryForm">
	<%@ include file="/common/messages.jsp"%>
		<form action="/mem/pwd" class="form" method="post" name="searchForm">
		     名称: <input type="text" name="name" value="${member.userName }" size="10"/>
			<img src="<c:url value="/static/images/icon/16x16/search.gif"/>" onclick="searchForm.submit();" align="middle"/>
		</form>
	</div>
	<div id="tableDiv">
	    <ec:table items="members" var="mem" action="${ctx}/mem"
			xlsFileName="会员列表.xls" 
			csvFileName="会员列表.csv"			
			useAjax="true"
			minColWidth="40"
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
			toolbarContent="navigation|pagejump|pagesize|refresh|export|extend|status"
		>
	        <ec:row>
	        	<ec:column property="rowcount" resizeColWidth="false" cell="rowCount" title="序号" sortable="false" width="50px"/>
	            <ec:column property="userName" title="会员名" width="120px"/>
	            <ec:column property="mobile" title="手机号" width="120px"/>
	            <ec:column property="email" title="邮箱地址" width="120px"/>
	            <ec:column property="addr" title="地址" width="120px"/>
	            <ec:column property="lastLogin" title="上次登陆时间" width="120px" >
	            	<fmt:formatDate value='${mem.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            <ec:column property="createTime" title="创建时间" width="120px" >
	            	<fmt:formatDate value='${mem.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            <ec:column property="state" title="账户状态" width="100px"/>
	            <ec:column property="edit" title="手机找回" sortable="false" viewsAllowed="html" width="60px">
	                <A href="<c:url value="/mem/pwd/mobile/${mem.id}"/>">
	                    <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
	                </A>
	            </ec:column>
	            <ec:column property="edit" title="邮箱找回" sortable="false" viewsAllowed="html" width="60px">
	                <A href="<c:url value="/mem/pwd/email/${mem.id}"/>">
	                    <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
	                </A>
	            </ec:column>
	        </ec:row>
	    </ec:table>
	    <c:if test="${empty members}">
			<font color="red">无符合条件的查询！</font>
		</c:if>
	</div>
</body>
</html>