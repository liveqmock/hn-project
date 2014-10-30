<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<%@ include file="/common/meta.jsp" %>
<title>房间管理 - 房型列表</title>
<style type="text/css">
div{text-align:center}
</style>
<script type="text/javascript">
var i = 5;
$(function(){
	setTimeout(timeCount, 1000);
});
function timeCount(){
	if(i < 1){
		location.href = '${ctx}/hotel';
		return;
	}
	$('#TimeSp').text(i);
	i--;
	setTimeout(timeCount, 1000);
}
</script>
</head>

<body>
<br/><br/><br/><br/>
<div>房间不存在或已被删除！</div><br/><br/>
<div> <span id="TimeSp">5</span>秒后 <a href="${ctx}/hotel">返回酒店列表</a> </div>
</body>
</html>
