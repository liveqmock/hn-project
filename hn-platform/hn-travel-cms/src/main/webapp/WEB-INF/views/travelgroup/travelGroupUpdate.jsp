<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>travelGroup修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/travelGroup/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${travelGroup.id }"/>
		<fieldset>
			<legend><small>travelGroup修改</small></legend>
			
			<!--  
			<div class="control-group">
				<label class="control-label">会员名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" value="${member.userName }" class="input-large" />
				</div>
			</div>
			-->
			
            <div class="control-group">
                <label class="control-label">名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="${travelGroup.name }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">市场价:</label>
                <div class="controls">
                    <input type="text" id="priceMarket" name="priceMarket" value="${travelGroup.priceMarket }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">现价:</label>
                <div class="controls">
                    <input type="text" id="priceNow" name="priceNow" value="${travelGroup.priceNow }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">支付方式:</label>
                <div class="controls">
                    <input type="text" id="payType" name="payType" value="${travelGroup.payType }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起价说明:</label>
                <div class="controls">
                    <input type="text" id="priceDescription" name="priceDescription" value="${travelGroup.priceDescription }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">优惠活动:</label>
                <div class="controls">
                    <input type="text" id="activity" name="activity" value="${travelGroup.activity }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起始省编号:</label>
                <div class="controls">
                    <input type="text" id="provinceNumStart" name="provinceNumStart" value="${travelGroup.provinceNumStart }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起始市编号:</label>
                <div class="controls">
                    <input type="text" id="cityNumStart" name="cityNumStart" value="${travelGroup.cityNumStart }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">起始区县编号:</label>
                <div class="controls">
                    <input type="text" id="townshipNumStart" name="townshipNumStart" value="${travelGroup.townshipNumStart }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的省编号:</label>
                <div class="controls">
                    <input type="text" id="provinceNumEnd" name="provinceNumEnd" value="${travelGroup.provinceNumEnd }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的市编号:</label>
                <div class="controls">
                    <input type="text" id="cityNumEnd" name="cityNumEnd" value="${travelGroup.cityNumEnd }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">目的区县编号:</label>
                <div class="controls">
                    <input type="text" id="townshipNumEnd" name="townshipNumEnd" value="${travelGroup.townshipNumEnd }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">简介:</label>
                <div class="controls">
                    <input type="text" id="description" name="description" value="${travelGroup.description }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">修改时间:</label>
                <div class="controls">
                    <input type="text" id="modifyTime" name="modifyTime" value="${travelGroup.modifyTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">创建时间:</label>
                <div class="controls">
                    <input type="text" id="createTime" name="createTime" value="${travelGroup.createTime }" class="input-large" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">状态:</label>
                <div class="controls">
                    <input type="text" id="state" name="state" value="${travelGroup.state }" class="input-large" />
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
