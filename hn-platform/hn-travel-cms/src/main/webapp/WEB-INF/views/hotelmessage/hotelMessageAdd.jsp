<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>hotelMessage新增</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/hotelMessage/add" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>hotelMessage新增</small></legend>
			<!--
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" class="input-large" />
				</div>
			</div>
			-->
            <div class="control-group">
                <label class="control-label">酒店ID:</label>
                <div class="controls">
                    <input type="text" id="hotelId" name="hotelId" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">设施服务:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdService" name="dataValueIdService" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">交通指南:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdTraffic" name="dataValueIdTraffic" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">公告:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdNotice" name="dataValueIdNotice" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" class="input-large" />
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
