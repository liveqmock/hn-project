<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>travelGroup新增</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/travelGroup/add" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>travelGroup新增</small></legend>
			<!--
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" class="input-large" />
				</div>
			</div>
			-->
            <div class="control-group">
                <label class="control-label">名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">市场价:</label>
                <div class="controls">
                    <input type="text" id="priceMarket" name="priceMarket" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">现价:</label>
                <div class="controls">
                    <input type="text" id="priceNow" name="priceNow" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">支付方式:</label>
                <div class="controls">
                    <input type="text" id="payType" name="payType" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起价说明:</label>
                <div class="controls">
                    <input type="text" id="priceDescription" name="priceDescription" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠活动:</label>
                <div class="controls">
                    <input type="text" id="activity" name="activity" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起始省编号:</label>
                <div class="controls">
                    <input type="text" id="provinceNumStart" name="provinceNumStart" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起始市编号:</label>
                <div class="controls">
                    <input type="text" id="cityNumStart" name="cityNumStart" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起始区县编号:</label>
                <div class="controls">
                    <input type="text" id="townshipNumStart" name="townshipNumStart" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的省编号:</label>
                <div class="controls">
                    <input type="text" id="provinceNumEnd" name="provinceNumEnd" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的市编号:</label>
                <div class="controls">
                    <input type="text" id="cityNumEnd" name="cityNumEnd" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的区县编号:</label>
                <div class="controls">
                    <input type="text" id="townshipNumEnd" name="townshipNumEnd" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">简介:</label>
                <div class="controls">
                    <input type="text" id="description" name="description" class="input-large" />
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
