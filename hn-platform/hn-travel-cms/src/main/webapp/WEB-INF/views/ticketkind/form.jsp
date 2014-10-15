<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery-validation/1.13.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/scripts/validate.ext.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.13.0/messages_zh.js" type="text/javascript"></script>
<script src="${ctx }/static/scripts/jquery/jquery.form.js" type="text/javascript"></script>
<script src="${ctx }/static/scripts/common.js" type="text/javascript"></script>
<title>门票管理 - 票种新增</title>
<style type="text/css">
th{width:80px; text-align:right;}
input.error { border: 1px dotted red; }
label.error { color: red }
.red{color:red}
</style>
<script type="text/javascript">
$(function(){
	$('form[name="postForm"]').validate({
		submitHandler : function(form) {
			submitForm(form, '${ctx }/ticketkind/${ticket.id}', '保存票种成功', '保存票种失败');
		}
	});
});
</script>
</head>

<body>
<c:choose>
 <c:when test="${ticket.scenicTicket != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/scenic"/>">景点管理</a> 
  - <a href="<c:url value="/scenicticket/${ticket.scenicTicket.scenic.id}"/>">${ticket.scenicTicket.scenic.name }</a> 
  - <a href="<c:url value="/ticketkind/${ticket.id}"/>">${ticket.scenicTicket.name }</a> 
  - 票种新增
 </div>
 </c:when>
 <c:when test="${ticket.itinerary != null}">
 <div class="pageTitle">您所在的位置：<a href="<c:url value="/itinerary"/>">旅行线路管理</a>
  - <a href="<c:url value="/ticketkind/${ticket.id}"/>">${ticket.itinerary.name }</a> 
  - 票种新增</div>
 </c:when>
</c:choose>

<form name="postForm" action="${ctx}/ticketkind/save/${ticket.id}" method="post" style="padding:5px">
 <table>
  <tr>
   <th><span class="red">*</span>名称:</th>
   <td><input type="text" name="name" style="width:300px" required value="${vo.name}" maxlength="50" /></td>
   <th><span class="red">*</span>市场价:</th>
   <td><input type="number" name="marketPrice" required value="${vo.marketPrice}" /></td>
  </tr>
  <tr>
   <th><span class="red">*</span>现价:</th>
   <td><input type="number" name="nowPrice" required value="${vo.nowPrice}" /></td>
   <th>状态:</th>
   <td><select name="status">
    <option value="0" <c:if test="${vo.status == 0}">selected="selected"</c:if>>上架</option>
    <option value="1" <c:if test="${vo.status == 1}">selected="selected"</c:if>>下架</option>
   </select></td>
  </tr>
  <tr>
   <th>描述:</th>
   <td colspan="3"><input type="text" name="desciption" style="width:500px" value="${vo.desciption}" maxlength="200" /></td>
  </tr>
  <tr><td colspan="4" align="center">
   <input type="hidden" name="id" value="${vo.id}" />
   <input type="hidden" name="createTime" value="<fmt:formatDate value='${vo.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
   <input class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
   <input class="btn" type="button" value="返回" onclick="location.href='<c:url value="/ticketkind/${ticket.id}"/>'"/>
  </td></tr>
 </table>
</form>
</body>
</html>
