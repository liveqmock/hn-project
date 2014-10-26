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
<title>酒店管理 - 酒店编辑</title>
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
		rules: {
			goodRate: {numeric: [3,2]}
		},
		submitHandler : function(form) {
			for(var i = 0; i < editors.length; i++)
				editors[i].sync();
			
			submitForm(form, '${ctx }/hotel', '保存酒店成功', '保存酒店失败');
		}
	});
});
</script>
</head>

<body>
<div class="pageTitle">您所在的位置：<a href="<c:url value="/hotel"/>">酒店管理</a> - 酒店${vo.id == null ? '新增' : '修改'}</div>	

<%@ include file="/common/messages.jsp"%>

<form name="postForm" action="${ctx}/hotel/save" method="post" style="padding:5px" enctype="multipart/form-data">
 <table>
  <tr>
   <th><span class="red">*</span>名称:</th>
   <td><input type="text" name="name" style="width:300px" required value="${vo.name}" maxlength="50" /></td>
   <th>小标题:</th>
   <td><input type="text" name="title" style="width:300px" value="${vo.title}" maxlength="100" /></td>
  </tr>
  <tr>
   <th><span class="red">*</span>主图:</th>
   <td><c:choose>
    <c:when test="${vo.imgUri != null && vo.imgUri != ''}">
     <a href="${vo.imgUri}" target="_blank"><img src="${vo.imgUri}" width="30" height="20"/></a>
     <input type="file" name="imgUriFile"/>
    </c:when>
    <c:otherwise><input type="file" name="imgUriFile" required/></c:otherwise>
   </c:choose></td>
   <th>支持的服务:</th>
   <td><input type="text" name="services" value="${vo.services}" maxlength="20" /></td>
  </tr>
  <tr>
   <th><span class="red">*</span>区县编码:</th>
   <td><input type="text" name="areaCode" required value="${vo.areaCode}" maxlength="50" /></td>
   <th><span class="red">*</span>地址:</th>
   <td><input type="text" name="address" style="width:300px" required value="${vo.address}" maxlength="200" /></td>
  </tr>
  <tr>
   <th><span class="red">*</span>经度:</th>
   <td><input type="number" name="longitude" required value="${vo.longitude}" /></td>
   <th><span class="red">*</span>纬度:</th>
   <td><input type="number" name="latitude" required value="${vo.latitude}" /></td>
  </tr>
  <tr>
   <th>起价:</th>
   <td><input type="number" name="lowPrice" value="${vo.lowPrice}" /></td>
   <th>好评率:</th>
   <td><input type="text" name="goodRate" value="${vo.goodRate}" range="[0,100]" /></td>
  </tr>
  <tr>
   <th>状态:</th>
   <td colspan="3"><select name="status">
    <option value="0" <c:if test="${vo.status == 0}">selected="selected"</c:if>>上架</option>
    <option value="1" <c:if test="${vo.status == 1}">selected="selected"</c:if>>下架</option>
   </select></td>
  </tr>
  <tr>
   <th>设施服务:</th>
   <td colspan="3"><textarea name="facility" style="width:680px;height:200px;visibility:hidden;">${vo.facility}</textarea></td>
  </tr>
  <tr>
   <th>交通位置:</th>
   <td colspan="3"><textarea name="position" style="width:680px;height:200px;visibility:hidden;">${vo.position}</textarea></td>
  </tr>
  <tr><td colspan="4" align="center">
   <input type="hidden" name="id" value="${vo.id}" />
   <input type="hidden" name="imgUri" value="${vo.imgUri}" />
   <input type="hidden" name="facilityId" value="${vo.facilityId}" />
   <input type="hidden" name="positionId" value="${vo.positionId}" />
   <input type="hidden" name="createTime" value="<fmt:formatDate value='${vo.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
   <input class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
   <input class="btn" type="button" value="返回" onclick="location.href='<c:url value="/hotel"/>'"/>
  </td></tr>
 </table>
</form>
</body>
</html>
