<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>line修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/line/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${line.id }"/>
		<fieldset>
			<legend><small>line修改</small></legend>
			
			<!--  
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			-->
			
            <div class="control-group">
                <label class="control-label">(自由行，跟团)ID:</label>
                <div class="controls">
                    <input type="text" id="travelId" name="travelId" value="${line.travelId }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">产品特色:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdFeature" name="dataValueIdFeature" value="${line.dataValueIdFeature }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">行程说明:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdDescription" name="dataValueIdDescription" value="${line.dataValueIdDescription }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">费用说明:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdCost" name="dataValueIdCost" value="${line.dataValueIdCost }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重要提示:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdImportant" name="dataValueIdImportant" value="${line.dataValueIdImportant }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的地情报:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdDestination" name="dataValueIdDestination" value="${line.dataValueIdDestination }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">公告:</label>
                <div class="controls">
                    <input type="text" id="dataValueIdNotice" name="dataValueIdNotice" value="${line.dataValueIdNotice }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">类型:</label>
                <div class="controls">
                    <input type="text" id="lineType" name="lineType" value="${line.lineType }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" value="${line.modifyTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" value="${line.createTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" value="${line.state }" class="input-large" />
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
