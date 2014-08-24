<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<STYLE type=text/css>
body {background-color: #f0f4fd;}
</STYLE>
<script type="text/javascript">
	function initMainFrame(){
		var mainFrameUrl="/admin/main.jsp";
		<c:if test="${fn:length(menus)>=0}">
			parent.window.document.getElementsByName("mainFrameset")[0].cols="130,*";
		</c:if>		
		<c:if test="${fn:length(menus)==0}">
			parent.window.document.getElementsByName("mainFrameset")[0].cols="1,*";
		</c:if>	
		<c:if test="${fn:length(menus)==1}">
			mainFrameUrl="${menus[0].resString}";
			parent.window.mainFrame.location=mainFrameUrl;
		</c:if>			
	}
</script>
</head>
<body onload="initMainFrame();">
	<div align="center">
		<DIV id=menu>
			<DL id=gallery>
				<DT><B>${fn:split(menus[0].name,'_')[0]}</B></DT>
				<c:forEach var="m" items="${menus}">
		  			<DD><a target="mainFrame" href="${m.resString}">${fn:split(m.name,'_')[1]}</a></DD>
				</c:forEach>				
			</DL>
		</DIV>
	</DIV>
</body>
</html>