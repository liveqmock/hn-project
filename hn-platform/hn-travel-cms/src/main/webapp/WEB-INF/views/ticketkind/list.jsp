<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<script src="${ctx }/static/scripts/common.js" type="text/javascript"></script>
<title>门票管理 - 票种列表</title>
<script type="text/javascript">
function del(id){
	delConfirm(id, '<c:url value="/ticketkind/delete"/>', '请至少选择一个票种',
			'<c:url value="/ticketkind/${ticket.id}"/>');
}
</script>
</head>

<body>
<c:choose>
 <c:when test="${ticket.scenicTicket != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/scenic"/>">景点管理</a> 
  - <a href="<c:url value="/scenicticket/${ticket.scenicTicket.scenic.id}"/>">${ticket.scenicTicket.scenic.name }</a> 
  - ${ticket.scenicTicket.name } 票种列表
 </div>
 </c:when>
 <c:when test="${ticket.itinerary != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/itinerary"/>">旅行线路管理</a>
  - ${ticket.itinerary.name } 票种列表
 </div>
 </c:when>
</c:choose>
	

<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
  <a href="<c:url value="/ticketkind/create/${ticket.id}"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></a>
  <a href="javascript:void(0)" onclick="del()"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></a>
</div>

<div id="tableDiv">
 <ec:table items="list" var="vo" action="/ticketkind/${ticket.id}" useAjax="true" minColWidth="80"
   doPreload="false" pageSizeList="15,20,50,100" rowsDisplayed="15" retrieveRowsCallback="limit" 
   sortRowsCallback="limit" sortable="false" generateScript="true" resizeColWidth="true" 		
   classic="true" filterable="flase" width="100%" height="420px" minHeight="360"
   toolbarContent="navigation|pagejump|pagesize|refresh |extend|status" >
  <ec:row>
   <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
    <input type="checkbox" name="selBox" value="${vo.id}" style="border:0px"/>
   </ec:column>
   <ec:column property="name" title="票种名称" width="300px" />
   <ec:column property="marketPrice" title="市场价" width="60px" sortable="false" />
   <ec:column property="nowPrice" title="现价" width="60px" sortable="false" />
   <ec:column property="desciption" title="描述" width="300px" sortable="false" />
   <ec:column property="status" title="状态" width="60px" sortable="false">
    <c:choose>
     <c:when test="${vo.status == 0}">上架</c:when>
     <c:when test="${vo.status == 1}">下架</c:when>
     <c:otherwise>其它</c:otherwise>
    </c:choose>
   </ec:column>
   <ec:column property="edit" title="操作" sortable="false" resizeColWidth="false" viewsAllowed="html" width="140px">
    <a href="<c:url value="/ticketkind/update/${ticket.id}/${vo.id}"/>">
     <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
    </a>
    <a href="javascript:void(0)" onclick="del('${vo.id}')">
     <img src="<c:url value="/static/images/icon/16x16/del.gif"/>" border="0"/>
    </a>
    <a href="<c:url value="/dateprice/${vo.id}"/>">日期价格</a>
   </ec:column>
  </ec:row>
 </ec:table>
	    
 <c:if test="${empty list}">
  <font color="red">无符合条件的查询！</font>
 </c:if>
	    
</div>
</body>
</html>
