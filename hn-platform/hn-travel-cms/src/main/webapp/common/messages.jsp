<%-- Error Messages --%>
<c:if test="${!empty error}">
<div id="result_div" class="result_div" style="visibility: hidden; width:200px; height:10px; left:15px; top:60px; background-color:#ffff66"></div>
	<script type="text/javascript">
		alert('${error}');
		//parent.frames[0].msgDiv.innerHTML='${error}';
		//window.setTimeout("parent.frames[0].msgDiv.innerHTML=''",4000);		
		//result_div.innerText='${error}';	
		//result_div.innerHTML='<h3>${error}</h3>';	
		//result_div.style.visibility='visible';
		//window.setTimeout("result_div.style.visibility='hidden'",4000);
	</script>
</c:if>
<c:if test="${!empty message}">

<div class="result_div" id="result_div"></div>
<script type="text/javascript">
	//alert('${message}');
	var dd = document.getElementById("result_div");
		//var dd=document.getElementsByTagName('DIV');
		var msgdiv=undefined;
		if(dd.length==0){
			alert('${message}');
		}else{
			msgdiv=dd;
			msgdiv.style['background-color']="#ffff66";
			msgdiv.innerHTML+="<span style='padding-left:50px;text-align:left'><font color='red'>${message}</font></span>";
			window.setTimeout("clearTip();",1000);
		}
		var clearTimes=0;
		function clearTip(){
			if(clearTimes<5){
				msgdiv.innerHTML=msgdiv.innerHTML+".";
				window.setTimeout("clearTip();",1000);
				clearTimes++
				return;
			}
			msgdiv.innerHTML=msgdiv.innerHTML.split("<")[0];
			msgdiv.style['background-color']="";
		}
		
		
	</script>
</c:if>