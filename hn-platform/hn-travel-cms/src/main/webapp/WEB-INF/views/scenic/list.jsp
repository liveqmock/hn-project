<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<title>景点管理 - 列表</title>
</head>

<body>
<div class="pageTitle">您所在的位置：景点管理 - 景点列表</div>	

<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
 <form action="/scenic" class="form" method="post" name="searchForm">
  id: <input type="text" name="id" value="${scenic.id }" size="10"/>
  <input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />
  
  <a href="<c:url value="${ctx}/scenic/create"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></a>
  <a href="javascript:void(0)" onclick="batch_do('删除资源','<c:url value="${ctx}/scenic/delete/" />');"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></a>
 </form>
</div>

<div id="tableDiv">
 <ec:table items="list" var="scenic" action="${ctx}/scenic" useAjax="true" minColWidth="80"
   doPreload="false" pageSizeList="15,20,50,100" rowsDisplayed="15" retrieveRowsCallback="limit" 
   sortRowsCallback="limit" sortable="true" generateScript="true" resizeColWidth="true" 		
   classic="true" filterable="flase" width="100%" height="420px" minHeight="360"
   toolbarContent="navigation|pagejump|pagesize|refresh |extend|status" >
  <ec:row>
   <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
    <input type="checkbox" name="itemlist" value="${scenic.id}" style="border:0px"/>
   </ec:column>
   <ec:column property="imgUri" resizeColWidth="false" title="主图" sortable="false" viewsAllowed="html" width="60px">
    <img src="${scenic.imgUri}" width="60" height="40"/>
   </ec:column>
   <ec:column property="name" title="景点名称" width="200px" />
   <ec:column property="title" title="小标题" width="200px" sortable="false" />
   <ec:column property="address" title="地址" width="240px" sortable="false">
   ${scenic.areaCode} ${scenic.address}
   </ec:column>
   <ec:column property="inTime" title="入园时间" width="80px" />
   <ec:column property="lowPrice" title="起价(元)" width="60px" />
   <ec:column property="goodRate" title="好评率(%)" width="60px" />
   <ec:column property="status" title="状态" width="60px" sortable="false">
    <c:choose>
     <c:when test="${scenic.status == 0}">上架</c:when>
     <c:when test="${scenic.status == 1}">下架</c:when>
     <c:otherwise>其它</c:otherwise>
    </c:choose>
   </ec:column>
   <ec:column property="edit" title="操作" sortable="false" resizeColWidth="false" viewsAllowed="html" width="60px">
    <a href="<c:url value="/scenic/update/${scenic.id}"/>">
     <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
    </a>
    <a href="javascript:void(0)" onclick="cfm_do('删除资源','<c:url value="/scenic/delete/${scenic.id}"/>')">
     <img src="<c:url value="/static/images/icon/16x16/del.gif"/>" border="0"/>
    </a>
   </ec:column>
  </ec:row>
 </ec:table>
	    
 <c:if test="${empty list}">
  <font color="red">无符合条件的查询！</font>
 </c:if>
	    
</div>
</body>
</html>
