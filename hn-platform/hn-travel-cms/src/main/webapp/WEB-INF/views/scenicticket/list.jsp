<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet">
<title>景点管理 - 门票列表</title>
<script type="text/javascript">
$.ajaxSetup({
	traditional: true
});
function del(id){
	var ids = [];
	if(id){
		ids.push(id);
	}else{
		$(':checkbox[name="selBox"]:checked').each(function(){
			ids.push(this.value);
		});
	}
	if(ids.length){
		if(confirm('是否确定删除？')){
			$.get('<c:url value="/scenicticket/delete"/>', {ids : ids}, function(data){
				if(data.success){
					alert('执行删除成功');
					location.href = '<c:url value="/scenicticket/${scenic.id}"/>';
				}else if(data.error)
					alert(data.error)
				else
					alert('执行删除出错');
			});
		}
	}else{
		alert('请至少选择一个门票')
	}
}
</script>
</head>

<body>
<div class="pageTitle">您所在的位置：景点管理 - <a href="<c:url value="/scenic"/>">${scenic.name }</a> - 门票列表</div>	

<div style="text-align: left;" class="queryForm">
<%@ include file="/common/messages.jsp"%>
  <a href="<c:url value="/scenicticket/create/${scenic.id}"/>"><img src="<c:url value="/static/images/icon/16x16/new.gif"/>" align="middle"></a>
  <a href="javascript:void(0)" onclick="del()"><img src="<c:url value="/static/images/icon/16x16/delete.gif"/>" align="middle"></a>
</div>

<div id="tableDiv">
 <ec:table items="list" var="vo" action="/scenicticket/${scenic.id}" useAjax="true" minColWidth="80"
   doPreload="false" pageSizeList="15,20,50,100" rowsDisplayed="15" retrieveRowsCallback="limit" 
   sortRowsCallback="limit" sortable="false" generateScript="true" resizeColWidth="true" 		
   classic="true" filterable="flase" width="100%" height="420px" minHeight="360"
   toolbarContent="navigation|pagejump|pagesize|refresh |extend|status" >
  <ec:row>
   <ec:column property="checkbox" resizeColWidth="false" title="选择" sortable="false" viewsAllowed="html" width="40px">
    <input type="checkbox" name="selBox" value="${vo.id}" style="border:0px"/>
   </ec:column>
   <ec:column property="name" title="门票名称" width="300px" />
   <ec:column property="type" title="类型" width="60px" sortable="false">
    <c:choose>
     <c:when test="${vo.status == 1}">单门票</c:when>
     <c:when test="${vo.status == 2}">组合套餐</c:when>
     <c:otherwise>其它</c:otherwise>
    </c:choose>
   </ec:column>
   <ec:column property="status" title="状态" width="60px" sortable="false">
    <c:choose>
     <c:when test="${vo.status == 0}">上架</c:when>
     <c:when test="${vo.status == 1}">下架</c:when>
     <c:otherwise>其它</c:otherwise>
    </c:choose>
   </ec:column>
   <ec:column property="edit" title="操作" sortable="false" resizeColWidth="false" viewsAllowed="html" width="60px">
    <a href="<c:url value="/scenicticket/update/${scenic.id}/${vo.id}"/>">
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
