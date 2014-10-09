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
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<title>景点管理 - 新增</title>
<style type="text/css">
th{width:80px; text-align:right;}
</style>
<script type="text/javascript">
KindEditor.ready(function(K) {
	var editor1 = K.create('textarea[name="notice"],textarea[name="introduce"],textarea[name="traffic"]', {
		cssPath : '/static/scripts/kindeditor/plugins/code/prettify.css',
		uploadJson : '/file/uploadJson',
		fileManagerJson : '/file/manager',
		allowFileManager : true
	});
	prettyPrint();
});
</script>
</head>

<body>
<div class="pageTitle">您所在的位置：景点管理 - 景点新增</div>	

<form name="postForm" action="${ctx}/scenic/create" method="post" style="padding:5px" enctype="multipart/form-data">
 <table>
  <tr>
   <th>名称:</th>
   <td><input type="text" name="name" style="width:300px" /></td>
   <th>小标题:</th>
   <td><input type="text" name="title" style="width:300px" /></td>
  </tr>
  <tr>
   <th>主图:</th>
   <td><input type="file" name="imgUriFile"/></td>
   <th>支持的服务:</th>
   <td><input type="text" name="services" /></td>
  </tr>
  <tr>
   <th>区县编码:</th>
   <td><input type="text" name="areaCode" /></td>
   <th>地址:</th>
   <td><input type="text" name="address" style="width:300px" /></td>
  </tr>
  <tr>
   <th>经度:</th>
   <td><input type="text" name="longitude" /></td>
   <th>纬度:</th>
   <td><input type="text" name="latitude" /></td>
  </tr>
  <tr>
   <th>起价:</th>
   <td><input type="text" name="lowPrice" /></td>
   <th>入园时间:</th>
   <td><input type="text" name="inTime" /></td>
  </tr>
  <tr>
   <th>状态:</th>
   <td><select name="status">
    <option value="0">上架</option>
    <option value="1">下架</option>
   </select></td>
   <th>好评率:</th>
   <td><input type="text" name="goodRate" /></td>
  </tr>
  <tr>
   <th>预订须知:</th>
   <td colspan="3"><textarea name="notice" style="width:680px;height:200px;visibility:hidden;"></textarea></td>
  </tr>
  <tr>
   <th>景点介绍:</th>
   <td colspan="3"><textarea name="introduce" style="width:680px;height:200px;visibility:hidden;"></textarea></td>
  </tr>
  <tr>
   <th>交通指南:</th>
   <td colspan="3"><textarea name="traffic" style="width:680px;height:200px;visibility:hidden;"></textarea></td>
  </tr>
  <tr><td colspan="4" align="center">
   <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
   <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
  </td></tr>
 </table>
</form>
</body>
</html>
