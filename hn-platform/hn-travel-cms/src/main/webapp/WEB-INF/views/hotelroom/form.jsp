<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<link href="${ctx}/static/scripts/widgets/extremecomponents/extremecomponents.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<%@ include file="/common/kindeditorMeta.jsp" %>
<script src="${ctx}/static/jquery-validation/1.13.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/scripts/validate.ext.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.13.0/messages_zh.js" type="text/javascript"></script>
<script src="${ctx }/static/scripts/jquery/jquery.form.js" type="text/javascript"></script>
<script src="${ctx }/static/scripts/common.js" type="text/javascript"></script>
<title>景点管理 - 门票编辑</title>
<style type="text/css">
th{width:80px; text-align:right;}
input.error { border: 1px dotted red; }
label.error { color: red }
.red{color:red}
</style>
<script type="text/javascript">
var editors;
KindEditor.ready(function(K) {
	editors = K.createMult('textarea', {
		cssPath : '${ctx }/static/scripts/kindeditor/plugins/code/prettify.css',
		uploadJson : '${ctx }/file/uploadJson',
		fileManagerJson : '${ctx }/file/manager',
		allowFileManager : true
	});
	prettyPrint();
});
$(function(){
	$('form[name="postForm"]').validate({
		submitHandler : function(form) {
			for(var i = 0; i < editors.length; i++)
				editors[i].sync();
			
			submitForm(form, '${ctx }/hotelroom/${hotel.id}', '保存房间成功', '保存房间失败');
		}
	});
});
</script>
</head>

<body>
<div class="pageTitle">您所在的位置：<a href="<c:url value="/hotel"/>">景点管理</a> 
 - <a href="<c:url value="/hotelroom/${hotel.id}"/>">${hotel.name }</a>
 - 房间${vo.id == null ? '新增' : '修改'}</div>	

<form name="postForm" action="${ctx}/hotelroom/save/${hotel.id}" method="post" style="padding:5px">
 <table>
  <tr>
   <th><span class="red">*</span>名称:</th>
   <td><input type="text" name="name" style="width:300px" required value="${vo.name}" maxlength="50" /></td>
   <th>状态:</th>
   <td><select name="status">
    <option value="0" <c:if test="${vo.status == 0}">selected="selected"</c:if>>上架</option>
    <option value="1" <c:if test="${vo.status == 1}">selected="selected"</c:if>>下架</option>
   </select></td>
  </tr>
  <tr>
   <th>房间信息:</th>
   <td colspan="3"><textarea name="info" style="width:680px;height:200px;visibility:hidden;">${vo.info}</textarea></td>
  </tr>
  <tr>
   <th>费用说明:</th>
   <td colspan="3"><textarea name="cost" style="width:680px;height:200px;visibility:hidden;">${vo.cost}</textarea></td>
  </tr>
  <tr>
   <th>预订须知:</th>
   <td colspan="3"><textarea name="notice" style="width:680px;height:200px;visibility:hidden;">${vo.notice}</textarea></td>
  </tr>
  <tr><td colspan="4" align="center">
   <input type="hidden" name="id" value="${vo.id}" />
   <input type="hidden" name="infoId" value="${vo.infoId}" />
   <input type="hidden" name="costId" value="${vo.costId}" />
   <input type="hidden" name="noticeId" value="${vo.noticeId}" />
   <input type="hidden" name="createTime" value="<fmt:formatDate value='${vo.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
   <input class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
   <input class="btn" type="button" value="返回" onclick="location.href='<c:url value="/hotelroom/${hotel.id}"/>'"/>
  </td></tr>
 </table>
</form>
</body>
</html>
