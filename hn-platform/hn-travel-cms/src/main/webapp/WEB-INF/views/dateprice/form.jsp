<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<link href="${ctx}/static/scripts/jqueryui/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery-validation/1.13.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/scripts/validate.ext.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.13.0/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/scripts/jquery/jquery.form.js" type="text/javascript"></script>
<script src="${ctx}/static/scripts/jqueryui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/scripts/jqueryui/datepicker-zh-CN.js" type="text/javascript"></script>
<script src="${ctx }/static/scripts/common.js" type="text/javascript"></script>
<title>票种管理 - 日期价格编辑</title>
<style type="text/css">
th{width:140px; text-align:right;}
input.error { border: 1px dotted red; }
label.error { color: red }
.red{color:red}
</style>
<script type="text/javascript">
$(function(){
	$('form[name="postForm"]').validate({
		submitHandler : function(form) {
			submitForm(form, '${ctx }/dateprice/${ticketKind.id}', '保存日期价格成功', '保存日期价格失败');
		}
	});
	$('#totalNum').change(function(){
		var v = $(this).val();
		var rn = $('#remainNum').attr('max', v.length ? v : 0);
		if(v.length && rn.val() > v)
			rn.val(v);
	});
	
	var dates = $("#startDate,#endDate");
	if(dates.length){
		dates.datepicker({
			minDate : dates.attr('minDate'),
			dateFormat : 'yy-mm-dd',
			defaultDate : dates.attr('minDate'),
			onSelect: function(selectedDate){  
				var option = this.id == "startDate" ? "minDate" : "maxDate";  
				dates.not(this).datepicker("option", option, selectedDate);  
			}  
		});
	}
	
});
</script>
</head>

<body>
<c:choose>
 <c:when test="${ticketKind.ticket.scenicTicket != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/scenic"/>">景点管理</a> 
  - <a href="<c:url value="/scenicticket/${ticketKind.ticket.scenicTicket.scenic.id}"/>">${ticketKind.ticket.scenicTicket.scenic.name }</a> 
  - <a href="<c:url value="/ticketkind/${ticketKind.ticket.scenicTicket.id}"/>">${ticketKind.ticket.scenicTicket.name }</a> 
  - <a href="<c:url value="/dateprice/${ticketKind.id}"/>">${ticketKind.name }</a>
  - 日期价格${vo.id == null ? '新增' : '修改'}
 </div>
 </c:when>
 <c:when test="${ticketKind.ticket.itinerary != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/itinerary"/>">旅行线路管理</a>
  - <a href="<c:url value="/ticketkind/${ticketKind.ticket.itinerary.id}"/>">${ticketKind.ticket.itinerary.name }</a> 
  - <a href="<c:url value="/dateprice/${ticketKind.id}"/>">${ticketKind.name }</a>
  - 日期价格${vo.id == null ? '新增' : '修改'}
  </div>
 </c:when>
</c:choose>

<form name="postForm" action="${ctx}/dateprice/save/${ticketKind.id}" method="post" style="padding:5px">
 <table>
<c:choose>
 <c:when test="${startDate == null}">
  <tr>
   <th><span class="red">*</span>日期:</th>
   <td colspan="3"><input type="text" name="pdate" style="width:232px" required value="<fmt:formatDate value='${vo.pdate}' pattern='yyyy-MM-dd'/>" readonly="readonly" /><span class="red">不允许修改</span></td>
  </tr>
 </c:when>
 <c:otherwise>
  <tr>
   <th><span class="red">*</span>起始日期(包含):</th>
   <td><input type="text" id="startDate" name="startDate" style="width:232px" required value="<fmt:formatDate value='${startDate}' pattern='yyyy-MM-dd'/>" minDate="<fmt:formatDate value='${startDate}' pattern='yyyy-MM-dd'/>" readonly="readonly" /></td>
   <th><span class="red">*</span>结束日期(包含):</th>
   <td><input type="text" id="endDate" name="endDate" style="width:232px" required value="" readonly="readonly" /></td>
  </tr>
 </c:otherwise>
</c:choose>
  <tr>
   <th><span class="red">*</span>市场价:</th>
   <td><input type="number" name="marketPrice" required value="${vo.marketPrice}" /></td>
   <th><span class="red">*</span>现价:</th>
   <td><input type="number" name="nowPrice" required value="${vo.nowPrice}" /></td>
  </tr>
  <tr>
   <th><span class="red">*</span>总数量${startDate == null ? '' : '(每天)'}:</th>
   <td><input id="totalNum" type="digits" name="totalNum" required value="${vo.totalNum}" min="2" /></td>
   <th><span class="red">*</span>剩余数量${startDate == null ? '' : '(每天)'}:</th>
   <td><input id="remainNum" type="digits" name="remainNum" required value="${vo.remainNum}" min="0" /></td>
  </tr>
  <tr><td colspan="4" align="center">
   <input type="hidden" name="id" value="${vo.id}" />
   <input class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
   <input class="btn" type="button" value="返回" onclick="location.href='<c:url value="/dateprice/${ticketKind.id}"/>'"/>
  </td></tr>
 </table>
</form>
</body>
</html>
