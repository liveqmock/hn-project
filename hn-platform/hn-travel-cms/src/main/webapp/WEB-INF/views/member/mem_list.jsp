<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<script src="/static/scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<title>会员管理</title>
</head>
<body onload="clearHiddenCheckBox()">
	<div class="pageTitle">您所在的位置：系统管理 - 会员管理</div>	
	<div style="text-align: left;" class="queryForm">
	<%@ include file="/common/messages.jsp"%>
		<form action="/mem" class="form" method="post" name="searchForm">
			会员名: <input type="text" name="userName" value="${member.userName }" size="10"/>
		 	手机号: <input type="text" name="mobile" value="${member.mobile }" size="10"/>
		 	邮箱地址: <input type="text" name="email" value="${member.email }" size="10"/>
		 	开始时间: <input type="text" name="startTime" value="<fmt:formatDate value='${member.startTime}' pattern='yyyy-MM-dd'/>" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" size="30" maxlength="30"/>
     		结束时间: <input type="text" name="endTime" value="<fmt:formatDate value='${member.endTime}' pattern='yyyy-MM-dd'/>" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" size="30" maxlength="30"/>
			<input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />
			<A href="<c:url value="${ctx}/mem/add"/>">
				<img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle">
			</A>
		</form>
	</div>
	<div id="tableDiv">
	    <ec:table items="members" var="mem" action="${ctx}/mem"
			xlsFileName="会员列表.xls" 
			csvFileName="会员列表.csv"			
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
			toolbarContent="navigation|pagejump|pagesize|refresh|export|extend|status"
		>
	        <ec:row>
	        	<ec:column property="rowcount" resizeColWidth="false" cell="rowCount" title="序号" sortable="false" width="40px"/>
	            <ec:column property="userName" title="会员名" width="120px"/>
	            <ec:column property="mobile" title="手机号" width="120px"/>
	            <ec:column property="email" title="邮箱地址" width="200px"/>
	            <ec:column property="addr" title="地址" width="200px"/>
	            <ec:column property="lastLogin" title="上次登陆时间" width="120px" >
	            	<fmt:formatDate value='${mem.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            <ec:column property="createTime" title="创建时间" width="120px" >
	            	<fmt:formatDate value='${mem.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            <ec:column property="state" title="账户状态" width="100px"/>
	            <ec:column property="edit" title="修改" sortable="false" viewsAllowed="html" width="40px">
	                <A href="<c:url value="/mem/update/${mem.id}"/>">
	                    <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
	                </A>
	            </ec:column>
	            <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
	                <input type="checkbox" name="itemlist" value="${mem.id}" style="border:0px"/>
	            </ec:column>
	        </ec:row>
	    </ec:table>
	    <c:if test="${empty members}">
			<font color="red">无符合条件的查询！</font>
		</c:if>
	</div>
</body>
</html>