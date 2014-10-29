<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<script src="${ctx }/static/scripts/common.js" type="text/javascript"></script>
<title>景点管理 - 景点列表</title>
<script type="text/javascript">
function del(id){
	delConfirm(id, '<c:url value="/scenic/delete"/>', '请至少选择一个景点',
			'<c:url value="/scenic"/>', '删除景点将同时删除该景点的门票，是否确定删除？');
}
</script>
</head>

<body>
<div class="pageTitle">您所在的位置：景点管理 - 景点列表</div>	

<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
 <form action="<c:url value="/scenic"/>" class="form" method="post" name="searchForm">
  景点名称: <input type="text" name="keyword" value="${param.keyword }"/>
  <input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />
  
  <a href="<c:url value="/scenic/create"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></a>
  <a href="javascript:void(0)" onclick="del()"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></a>
 </form>
</div>

<div id="tableDiv">
 <ec:table items="list" var="scenic" action="/scenic" useAjax="true" minColWidth="80"
   doPreload="false" pageSizeList="15,20,50,100" rowsDisplayed="15" retrieveRowsCallback="limit" 
   sortRowsCallback="limit" sortable="false" generateScript="true" resizeColWidth="true" 		
   classic="true" filterable="flase" width="100%" height="420px" minHeight="360"
   toolbarContent="navigation|pagejump|pagesize|refresh |extend|status" >
  <ec:row>
   <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
    <input type="checkbox" name="selBox" value="${scenic.id}" style="border:0px"/>
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
   <ec:column property="lowPrice" title="起价(元)" width="70px" />
   <ec:column property="goodRate" title="好评率(%)" width="80px" />
   <ec:column property="status" title="状态" width="60px" sortable="false">
    <c:choose>
     <c:when test="${scenic.status == 0}">上架</c:when>
     <c:when test="${scenic.status == 1}">下架</c:when>
     <c:otherwise>其它</c:otherwise>
    </c:choose>
   </ec:column>
   <ec:column property="edit" title="操作" sortable="false" resizeColWidth="false" viewsAllowed="html" width="120px">
    <a href="<c:url value="/scenic/update/${scenic.id}"/>">
     <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
    </a>
    <a href="javascript:void(0)" onclick="del('${scenic.id}')">
     <img src="<c:url value="/static/images/icon/16x16/del.gif"/>" border="0"/>
    </a>
    <a href="<c:url value="/scenicticket/${scenic.id}"/>">门票</a>
    <a href="http://iwtour.com.cn/scenic/detail/${scenic.id}.htm" target="_blank">预览</a>
   </ec:column>
  </ec:row>
 </ec:table>
	    
 <c:if test="${empty list}">
  <font color="red">无符合条件的查询！</font>
 </c:if>
	    
</div>
</body>
</html>
