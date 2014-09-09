<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<title>album管理</title>
</head>
<body onload="clearHiddenCheckBox()">
<div class="pageTitle">您所在的位置：系统管理 - album管理</div>	
<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
<form action="/album" class="form" method="post" name="searchForm">
     id: <input type="text" name="id" value="${album.id }" size="10"/>
<input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />
<A href="<c:url value="${ctx}/album/toAdd"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></A>
<A href="javascript:batch_do('删除资源','<c:url value="${ctx}/album/delete/" />');"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></A>
</form>
</div>
	<div id="tableDiv">
	    <ec:table items="albumList" var="album"
	              action="${ctx}/album"
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
	            	<fmt:formatDate value='${album.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
	            </ec:column>
	            
            <ec:column property="objectId" title="评论对象ID" width="120px" />
            <ec:column property="name" title="名称" width="120px" />
            <ec:column property="description" title="说明" width="120px" />
            <ec:column property="originalPath" title="原图地址" width="120px" />
            <ec:column property="largePath" title="大图地址" width="120px" />
            <ec:column property="middlePath" title="中图地址" width="120px" />
            <ec:column property="smallPath" title="小图地址" width="120px" />
            <ec:column property="albumType" title="类型" width="120px" />
            <ec:column property="modifyTime" title="修改时间" width="120px" />
            <ec:column property="createTime" title="创建时间" width="120px" />
            <ec:column property="state" title="状态" width="120px" />
        
	            
	            <ec:column property="edit" title="查看" sortable="false" viewsAllowed="html" width="40px">
	                <A href="<c:url value="/album/getById/${album.id}"/>">查看</A>
	            </ec:column>
	            <ec:column property="edit" title="修改" sortable="false" viewsAllowed="html" width="40px">
	                <A href="<c:url value="/album/toUpdate/${album.id}"/>">
	                    <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
	                </A>
	            </ec:column>
	            <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
	                <input type="checkbox" name="itemlist" value="${album.id}" style="border:0px"/>
	            </ec:column>
	        </ec:row>
	    </ec:table>
	    <c:if test="${empty albumList}">
			<font color="red">无符合条件的查询！</font>
		</c:if>
	    
	</div>
</body>
</html>
