<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<script src="/static/scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<title>我的收藏</title>
</head>
<body onload="clearHiddenCheckBox()">
	<div class="pageTitle">您所在的位置：系统管理-我的收藏</div>	
	<div style="text-align: left;" class="queryForm">
	<%@ include file="/common/messages.jsp"%>
		<form action="/mem/favorites" class="form" method="post" name="searchForm">
		 	会员ID: <input type="text" name="memId" value="${memId }" size="10"/>
		 	<input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />
			<A href="<c:url value="${ctx}/mem/add"/>">
				<img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle">
			</A>
		</form>
	</div>
	<div id="tableDiv">
	    <ec:table items="favorites" var="favor" action="${ctx}/mem/favorites"
			xlsFileName="我的收藏列表.xls" 
			csvFileName="我的收藏列表.csv"			
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
	            <ec:column property="memId" title="会员名" width="120px"/>
	            <ec:column property="itemId" title="商品名称" width="120px"/>
	            
	            <ec:column property="createTime" title="创建时间" width="120px" >
	            	<fmt:formatDate value='${favor.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            <ec:column property="modifyTime" title="上次变动时间" width="120px" >
	            	<fmt:formatDate value='${favor.modifyTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            <ec:column property="state" title="账户状态" width="100px"/>
	            <ec:column property="edit" title="修改" sortable="false" viewsAllowed="html" width="40px">
	                <A href="<c:url value="/mem/update/${favor.id}"/>">
	                    <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
	                </A>
	            </ec:column>
	            <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
	                <input type="checkbox" name="itemlist" value="${favor.id}" style="border:0px"/>
	            </ec:column>
	        </ec:row>
	    </ec:table>
	    <c:if test="${empty favorites}">
			<font color="red">无符合条件的查询！</font>
		</c:if>
	</div>
</body>
</html>