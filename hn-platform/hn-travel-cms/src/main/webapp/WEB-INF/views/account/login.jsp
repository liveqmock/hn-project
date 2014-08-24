<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp" %>
		<title>海南本地旅游网 - 后台管理系统 - 登录</title>
	    <style type="text/css">
		<!--
		.STYLE2 {font-weight: bold; font-size: x-large;}
div.error {
	width: 260px;
	border: 2px solid green;
	background-color: yellow;
	text-align: left;
	background: url(/static/images/frontpage/images/icon_message.gif) no-repeat -24px 0px;
}
div.hide {display: none;}
		-->
        </style>
        <script type="text/javascript">
        	var wp=window;
        	i=0;
        	while(wp.parent!=null&&wp.parent!=wp){
        		wp=wp.parent;
        	}
        	if(wp!=null&&wp!=window)
        		wp.location="/";
        </script>
	</head>
	<body><br/><br/><br/><br/>
		<form action="${ctx}/login" method="POST">
			<table width="800" height="486" border="0" align="center">
		      <tr>
		        <td height="18" colspan="9" bgcolor="#d6d6d6">&nbsp;</td>
		      </tr>
		      <tr>
		        <td height="92" colspan="9"><p>&nbsp;</p>
		        <p>&nbsp;</p>
		        <p>&nbsp;</p>
		        <p>&nbsp;</p></td>
		      </tr>
		      <tr>
		        <td width="1" rowspan="6">&nbsp;</td>
		        <td height="81" colspan="3"><!-- <img src="${ctx}/static/images/2.JPG" alt="logo" width="214" height="60"> --></td>
		        <td width="1" rowspan="6">&nbsp;</td>
		        <td colspan="3" align="center" valign="middle" class="STYLE2">海南本地旅游网</td>
		        <td width="4" rowspan="7">&nbsp;</td>
		      </tr>
		      <tr>
		        <td width="91" height="24">&nbsp;</td>
		        <td height="24" colspan="2">&nbsp;</td>
		        <td colspan="3" rowspan="6"><img src="${ctx}/static/images/1.JPG" alt="login" width="433" height="113"></td>
		      </tr>
		      <tr>
		        <td width="91" height="26">操作员</td>
		        <td colspan="2">
		        	<input type='text' size="16" style="width:120px;" maxlength="22" id="username" name="username"  value="${username}" />		        		
			    </td>
		      </tr>
		      <tr>
		        <td width="91" height="19">&nbsp;</td>
		        <td colspan="2">&nbsp;</td>
		      </tr>
		      <tr>
		        <td width="91">密码</td>
		        <td colspan="2"><input type='password' id="password" name="password" size="16" style="width:120px;" maxlength="16" /></td>
		      </tr>
		      <tr>
		        <td colspan="2" align="center">&nbsp;
		        	<%
					String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		        	if(error != null){
					%>
						<font color="red" size="2pt">
		        		用户名或密码错误，请重试.
		        		</font>
					<%
					}
					%>
		         	&nbsp;
		        </td>
		      </tr>		      
		      <tr>
		        <td width="91">&nbsp;</td>
		        <td align="right"><input name="submit" type="submit" value=" 登 录 "></td>
		        <td width="160" align="center"><input name="reset" type="reset" value=" 重 置 "></td>
		      </tr>
			  <tr>
			  <td height="144" colspan="9" bgcolor="#FFFFFF"><p>&nbsp;</p>
			    <p>&nbsp;</p>
			    <p>&nbsp;</p>
			    <p>&nbsp;</p></td>
			  </tr>
			  <tr>
		        <td height="18" colspan="9" align="right" valign="bottom" bgcolor="#f2f2f2">海南本地旅游网 建议在IE6 1024*768分辨率下使用</td>
		      </tr>
		    </table>
		</form>
	</body>
</html>