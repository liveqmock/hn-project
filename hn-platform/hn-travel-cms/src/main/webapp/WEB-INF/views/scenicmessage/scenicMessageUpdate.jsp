<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>scenicMessage修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/scenicMessage/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${scenicMessage.id }"/>
		<fieldset>
			<legend><small>scenicMessage修改</small></legend>
			
			<!--  
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			-->
			
            <div class="control-group">
                <label class="control-label">景点ID:</label>
                <div class="controls">
                    <input type="text" id="scenicId" name="scenicId" value="${scenicMessage.scenicId }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">订票须知:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdKnow" name="dataValueIdKnow" value="${scenicMessage.dataValueIdKnow }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">景点介绍:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdIntroduce" name="dataValueIdIntroduce" value="${scenicMessage.dataValueIdIntroduce }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">交通指南:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdTraffic" name="dataValueIdTraffic" value="${scenicMessage.dataValueIdTraffic }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">公告:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdNotice" name="dataValueIdNotice" value="${scenicMessage.dataValueIdNotice }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" value="${scenicMessage.modifyTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" value="${scenicMessage.createTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" value="${scenicMessage.state }" class="input-large" />
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
