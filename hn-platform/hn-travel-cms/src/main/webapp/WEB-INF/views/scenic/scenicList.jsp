<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<title>scenic管理</title>
</head>
<body onload="clearHiddenCheckBox()">
<div class="pageTitle">您所在的位置：系统管理 - scenic管理</div>	
<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
<form action="/scenic" class="form" method="post" name="searchForm">
     id: <input type="text" name="id" value="${scenic.id }" size="10"/>
<input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />
<A href="<c:url value="${ctx}/scenic/toAdd"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></A>
<A href="javascript:batch_do('删除资源','<c:url value="${ctx}/scenic/delete/" />');"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></A>
</form>
</div>
	<div id="tableDiv">
	    <ec:table items="scenicList" var="scenic"
	              action="${ctx}/scenic"
			xlsFileName="用户列表.xls" 
			csvFileName="用户列表.csv"			
			useAjax="true"
			minColWidth="80"
			doPreload="false"
			pageSizeList="13,20,50,100,500,all" 
			rowsDisplayed="13" 		
			retrieveRowsCallback="limit" 
			sortRowsCallback="limit"		
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
	            <ec:column property="id" title="登录名" width="120px" sortable="true"/>
	            <ec:column property="createTime" title="创建时间" width="120px" >
	            	<fmt:formatDate value='${scenic.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            
            <ec:column property="name" title="景点名称" width="120px" />
            <ec:column property="address" title="景点地址" width="120px" />
            <ec:column property="priceStart" title="起始价格" width="120px" />
            <ec:column property="longitude" title="景点经度" width="120px" />
            <ec:column property="latitude" title="景点纬度" width="120px" />
            <ec:column property="provinceNum" title="省编号" width="120px" />
            <ec:column property="cityNum" title="市编号" width="120px" />
            <ec:column property="townshipNum" title="区县编号" width="120px" />
            <ec:column property="description" title="简介" width="120px" />
            <ec:column property="modifyTime" title="修改时间" width="120px" />
            <ec:column property="createTime" title="创建时间" width="120px" />
            <ec:column property="state" title="状态" width="120px" />
        
	            
	            <ec:column property="edit" title="查看" sortable="false" viewsAllowed="html" width="40px">
	                <A href="<c:url value="/scenic/getById/${scenic.id}"/>">查看</A>
	            </ec:column>
	            <ec:column property="edit" title="修改" sortable="false" viewsAllowed="html" width="40px">
	                <A href="<c:url value="/scenic/toUpdate/${scenic.id}"/>">
	                    <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
	                </A>
	            </ec:column>
	            <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
	                <input type="checkbox" name="itemlist" value="${scenic.id}" style="border:0px"/>
	            </ec:column>
	        </ec:row>
	    </ec:table>
	    <c:if test="${empty scenicList}">
			<font color="red">无符合条件的查询！</font>
		</c:if>
	    
	</div>
</body>
</html>
