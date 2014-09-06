<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>会员管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/mem/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${member.id }"/>
		<fieldset>
			<legend><small>会员管理</small></legend>
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">手机:</label>
				<div class="controls">
					<input type="text" id="mobile" name="mobile" value="${member.mobile }" class="input-large" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">邮箱:</label>
				<div class="controls">
					<input type="text" id="email" name="email" value="${member.email }" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系地址:</label>
				<div class="controls">
					<input type="text" id="addr" name="addr" value="${member.addr }" class="input-large required"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#userName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
