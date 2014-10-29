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
<title>景点管理 - 景点编辑</title>
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
			
			submitForm(form, '${ctx }/scenic', '保存景点成功', '保存景点失败');
		}
	});
});
</script>
</head>

<body>
<div class="pageTitle">您所在的位置：<a href="<c:url value="/scenic"/>">景点管理</a> - 景点${vo.id == null ? '新增' : '修改'}</div>	

<%@ include file="/common/messages.jsp"%>

<form name="postForm" action="${ctx}/scenic/save" method="post" style="padding:5px" enctype="multipart/form-data">
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
   <th>入园时间:</th>
   <td><input type="text" name="inTime" value="${vo.inTime}" maxlength="20" /></td>
  </tr>
  <tr>
   <th>状态:</th>
   <td><select name="status">
    <option value="0" <c:if test="${vo.status == 0}">selected="selected"</c:if>>上架</option>
    <option value="1" <c:if test="${vo.status == 1}">selected="selected"</c:if>>下架</option>
   </select></td>
   <th>好评率:</th>
   <td><input type="text" name="goodRate" value="${vo.goodRate}" range="[0,100]" /></td>
  </tr>
  <tr>
   <th>预订须知:</th>
   <td colspan="3"><textarea name="notice" style="width:680px;height:200px;visibility:hidden;">${vo.notice}</textarea></td>
  </tr>
  <tr>
   <th>景点介绍:</th>
   <td colspan="3"><textarea name="introduce" style="width:680px;height:200px;visibility:hidden;">${vo.introduce}</textarea></td>
  </tr>
  <tr>
   <th>交通指南:</th>
   <td colspan="3"><textarea name="traffic" style="width:680px;height:200px;visibility:hidden;">${vo.traffic}</textarea></td>
  </tr>
  <tr><td colspan="4" align="center">
   <input type="hidden" name="id" value="${vo.id}" />
   <input type="hidden" name="imgUri" value="${vo.imgUri}" />
   <input type="hidden" name="trafficId" value="${vo.trafficId}" />
   <input type="hidden" name="noticeId" value="${vo.noticeId}" />
   <input type="hidden" name="introduceId" value="${vo.introduceId}" />
   <input type="hidden" name="createTime" value="<fmt:formatDate value='${vo.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
   <input class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
   <input class="btn" type="button" value="返回" onclick="location.href='<c:url value="/scenic"/>'"/>
  </td></tr>
 </table>
</form>
</body>
</html>
