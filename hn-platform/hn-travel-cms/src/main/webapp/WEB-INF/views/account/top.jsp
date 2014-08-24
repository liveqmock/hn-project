<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<%@ include file="/common/meta.jsp" %>
   <link href="${ctx}/static/styles/admin/style.css" type="text/css" rel="stylesheet">
<SCRIPT FOR="window" EVENT="onunload"> 
if(!userQuit){	 
	//window.open("/j_acegi_logout","newWindow","height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
    //alert("关闭");       		
}
</SCRIPT>
</head>
<body>
<TABLE width="100%" height="88" border="0" cellspacing="0" cellpadding="0">
<tr>
   	<td  background="${ctx }/static/images/frame/top1_r1_c1.jpg">
      	<TABLE width="100%" height="53" border="0" cellpadding="0" cellspacing="0"  style="background-position:bottom right;">
		    <tr>
				<TD><img src="${ctx }/static/images/frame/top1_r_u.jpg" width="405" height="55"></TD>
				<TD align="right" nowrap="true">
					<TABLE cellspacing="3">
						<TR>
							<TD class="td_b1"> 
								版本号: ${version} |
								操作员: ${user.name} | 登录时间:${loginTime} &nbsp;&nbsp;
								<a  href="/sys/user/changepwd" target="mainFrame">
								[修改密码]</a>
		                       	<A href="javascript:userQuit=true;top.document.location.href='/logout';">
								[退出系统]</a>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
		<TABLE width="100%" height="30" border="0" cellpadding="0" cellspacing="0" Background="${ctx }/static/images/frame/top_r2_c3.jpg">
			<TR>					
				<TD>
					<div id="navmenu" style="left:0;">
					<c:forEach var="m" items="${menus}">
						<a href="/res/list/${m.id }" target="leftFrame">${m.name}</a>
					</c:forEach>
					</div>
				</TD>	
				<TD align="right" valign="middle" nowrap="true"></TD>
			</TR>
		</TABLE>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td height="2"></td>
			</tr>
		</TABLE>
	</td>
</tr>
</TABLE>
</body>
</html>