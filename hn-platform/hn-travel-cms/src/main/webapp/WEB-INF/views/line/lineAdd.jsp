<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>line新增</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/line/add" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>line新增</small></legend>
			<!--
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" class="input-large" />
				</div>
			</div>
			-->
            <div class="control-group">
                <label class="control-label">(自由行，跟团)ID:</label>
                <div class="controls">
                    <input type="text" id="travelId" name="travelId" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">产品特色:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdFeature" name="dataValueIdFeature" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">行程说明:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdDescription" name="dataValueIdDescription" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">费用说明:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdCost" name="dataValueIdCost" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重要提示:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdImportant" name="dataValueIdImportant" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的地情报:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdDestination" name="dataValueIdDestination" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">公告:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdNotice" name="dataValueIdNotice" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">类型:</label>
                <div class="controls">
                    <input type="text" id="lineType" name="lineType" class="input-large" />
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
