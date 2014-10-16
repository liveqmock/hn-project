<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<link href="${ctx}/static/scripts/jqueryui/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<script src="${ctx}/static/scripts/jqueryui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/scripts/jqueryui/datepicker-zh-CN.js" type="text/javascript"></script>
<script src="${ctx }/static/scripts/common.js" type="text/javascript"></script>
<title>门票管理 - 票种列表</title>
<script type="text/javascript">
function del(id){
	delConfirm(id, '<c:url value="/dateprice/delete"/>', '请至少选择一个日期价格',
			'<c:url value="/dateprice/${ticketKind.id}"/>');
}
$(function(){
	var startDate = $("#startDate"), endDate = $('#endDate');
	startDate.datepicker({
		minDate : startDate.attr('minDate'),
		maxDate : endDate.val(),
		dateFormat : 'yy-mm-dd',
		defaultDate : endDate.attr('minDate'),
		onSelect: function(selectedDate){  
			endDate.datepicker("option", "minDate", selectedDate);  
		}
	});
	endDate.datepicker({
		minDate : startDate.val() || startDate.attr('minDate'),
		dateFormat : 'yy-mm-dd',
		onSelect: function(selectedDate){  
			startDate.datepicker("option", "maxDate", selectedDate);  
		}
	});
});
</script>
</head>

<body>
<c:choose>
 <c:when test="${ticketKind.ticket.scenicTicket != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/scenic"/>">景点管理</a> 
  - <a href="<c:url value="/scenicticket/${ticketKind.ticket.scenicTicket.scenic.id}"/>">${ticketKind.ticket.scenicTicket.scenic.name }</a> 
  - <a href="<c:url value="/ticketkind/${ticketKind.ticket.scenicTicket.id}"/>">${ticketKind.ticket.scenicTicket.name }</a> 
  - ${ticketKind.name } 日期价格列表
 </div>
 </c:when>
 <c:when test="${ticketKind.ticket.itinerary != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/itinerary"/>">旅行线路管理</a>
  - <a href="<c:url value="/ticketkind/${ticketKind.ticket.itinerary.id}"/>">${ticketKind.ticket.itinerary.name }</a> 
  - ${ticketKind.name } 日期价格列表
  </div>
 </c:when>
</c:choose>
	

<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
 <form action="/dateprice/${ticketKind.id}" class="form" method="post" name="searchForm">
  起始日期: <input type="text" id="startDate" name="startPdate" style="width:132px;z-index:10;position:relative;" required value="${param.startPdate}" minDate="<fmt:formatDate value='${startDate}' pattern='yyyy-MM-dd'/>" /> 
  - <input type="text" id="endDate" name=endPdate style="width:132px;z-index:10;position:relative;" required value="${param.endPdate}" />
  <input type="image" src="<c:url value="/static/images/icon/16x16/search.gif"/>"  class="nullBorder" onclick="searchForm.submit();" align="middle" />

  <a href="<c:url value="/dateprice/create/${ticketKind.id}"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></a>
  <a href="javascript:void(0)" onclick="del()"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></a>
 </form>
</div>

<div id="tableDiv">
 <ec:table items="list" var="vo" action="/dateprice/${ticketKind.id}" useAjax="true" minColWidth="80"
   doPreload="false" pageSizeList="15,20,50,100" rowsDisplayed="15" retrieveRowsCallback="limit" 
   sortRowsCallback="limit" sortable="false" generateScript="true" resizeColWidth="true" 		
   classic="true" filterable="flase" width="100%" height="420px" minHeight="360"
   toolbarContent="navigation|pagejump|pagesize|refresh |extend|status" >
  <ec:row>
   <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
    <input type="checkbox" name="selBox" value="${vo.id}" style="border:0px"/>
   </ec:column>
   <ec:column property="pdate" title="日期" width="100px">
    <fmt:formatDate value='${vo.pdate}' pattern='yyyy-MM-dd'/>
   </ec:column>
   <ec:column property="marketPrice" title="市场价" width="80px" sortable="false" />
   <ec:column property="nowPrice" title="现价" width="80px" sortable="false" />
   <ec:column property="totalNum" title="总数量" width="80px" sortable="false" />
   <ec:column property="remainNum" title="剩余数量" width="80px" sortable="false" />
   <ec:column property="edit" title="操作" sortable="false" resizeColWidth="false" viewsAllowed="html" width="60px">
    <a href="<c:url value="/dateprice/update/${ticketKind.id}/${vo.id}"/>">
     <img src="<c:url value="/static/images/icon/16x16/modify.gif"/>" border="0"/>
    </a>
    <a href="javascript:void(0)" onclick="del('${vo.id}')">
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
