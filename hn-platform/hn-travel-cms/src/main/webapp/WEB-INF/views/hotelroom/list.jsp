<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<script src="${ctx }/static/scripts/common.js" type="text/javascript"></script>
<title>酒店管理 - 房间列表</title>
<script type="text/javascript">
function del(id){
	delConfirm(id, '<c:url value="/hotelroom/delete"/>', '请至少选择一个房间',
			'<c:url value="/hotelroom/${hotel.id}"/>', '删除门票将同时删除该房间的房型，是否确定删除？');
}
</script>
</head>

<body>
<div class="pageTitle">您所在的位置：<a href="<c:url value="/hotel"/>">酒店管理</a> 
 - ${hotel.name } 房间列表</div>	

<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
  <a href="<c:url value="/hotelroom/create/${hotel.id}"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></a>
  <a href="javascript:void(0)" onclick="del()"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></a>
</div>

<div id="tableDiv">
 <ec:table items="list" var="vo" action="/hotelroom/${hotel.id}" useAjax="true" minColWidth="80"
   doPreload="false" pageSizeList="15,20,50,100" rowsDisplayed="15" retrieveRowsCallback="limit" 
   sortRowsCallback="limit" sortable="false" generateScript="true" resizeColWidth="true" 		
   classic="true" filterable="flase" width="100%" height="420px" minHeight="360"
   toolbarContent="navigation|pagejump|pagesize|refresh |extend|status" >
  <ec:row>
   <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
    <input type="checkbox" name="selBox" value="${vo.id}" style="border:0px"/>
   </ec:column>
   <ec:column property="name" title="房间名称" width="300px" />
   <ec:column property="status" title="状态" width="60px" sortable="false">
    <c:choose>
     <c:when test="${vo.status == 0}">上架</c:when>
     <c:when test="${vo.status == 1}">下架</c:when>
     <c:otherwise>其它</c:otherwise>
    </c:choose>
   </ec:column>
   <ec:column property="edit" title="操作" sortable="false" resizeColWidth="false" viewsAllowed="html" width="120px">
    <a href="<c:url value="/hotelroom/update/${hotel.id}/${vo.id}"/>">
     <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
    </a>
    <a href="javascript:void(0)" onclick="del('${vo.id}')">
     <img src="<c:url value="/static/images/icon/16x16/del.gif"/>" border="0"/>
    </a>
    <a href="<c:url value="/roomkind/${vo.id}"/>">房型</a>
   </ec:column>
  </ec:row>
 </ec:table>
	    
 <c:if test="${empty list}">
  <font color="red">无符合条件的查询！</font>
 </c:if>
	    
</div>
</body>
</html>
