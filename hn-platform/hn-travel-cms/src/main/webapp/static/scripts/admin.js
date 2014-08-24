//后台管理界面用到的一些函数
function batch_do(entityName, action)
{
    if (confirm("确定要" + entityName + "?"))
    {
        if (!atleaseOneCheck())
        {
            alert('请至少选择一' + entityName + '！');
            return;
        }
        var form = document.forms.ec;
        form.action = action;
        form.submit();
    }
};
function ajax_batch_do(entityName, url, callBackForm)
{
    if (confirm("确定要" + entityName + "?"))
    {
        if (!atleaseOneCheck())
        {
            alert('请至少选择一' + entityName + '！');
            return;
        }
        jQuery.ajax({
			type : 'post',
			url : url,
			data : jQuery("form[id='ec']").serialize(),
			success : function(d) {
				if(d.flag == 0) {
					alert(d.msg);
					jQuery("form[name='" + callBackForm + "']").submit();
				}else{
					jQuery.messager.alert(entityName+'失败',d.msg,'error');								
				}
			}
		});
    }
};
function cfm_do(entityName, action)
{
    if (confirm('确定要' + entityName + '?'))
    {
        var form = document.forms.ec;
        form.action = action;
        form.submit();
    }
};

function openwin(url, width, height, scroll)
{
    /*if (!document.all)
    {
        document.captureEvents(Event.MOUSEMOVE);
        x = e.pageX + width - 30;
        y = e.pageY + height - 30;
    }
    else
    {
        x = document.body.scrollLeft + event.clientX + width - 30;
        y = document.body.scrollTop + event.clientY + height - 30;
    }*/
    window.open(url, "newWindow", "height=" + height + ", width=" + width + ", toolbar =no, menubar=no, scrollbars=" + scroll + ", resizable=no, location=no, status=no, top=100, left=300") //写成一行
}

//checkbox中至少有一项被选中
function atleaseOneCheck()
{
    var items = document.getElementsByName('itemlist');
    if(items.length>0){
	    for (var i = 0; i < items.length; i++)
	    {
	        if (items[i].checked == true)
	        {
	            return true;
	        }
	    }
	}else{
		if(items.checked == true){
			  return true;		   	 
		}
	}
    return false;
}

function clearHiddenCheckBox()
{
    var items = document.getElementsByName('itemlist');
    if(items.length>0){
	    for (var i = 0; i < items.length; i++)
	    {					
	        if (items[i].tagName=="INPUT"&&items[i].type=="hidden")
	        {
	            items[i].value="";
	        }
	    }
	}
};

function edit(form,action){
	if(action=='edit'){	
		show(form,"none","");
	}else if(action=='save'){
		save(form);
	}else if(action=='cancel'){
	 	if(!window.confirm("确定取消编辑吗？"))
			return;			
		show(form,"","none");
	}else if(action=='create'){		
	    save(form);
	}else if(action=='update'){		
	    save(form, true);
	}else	
		return;	
};
//给label赋值
function setLabel(form){
	var tdList = form.childNodes;
	for(var i=0;i<tdList.length;i++){
		var inputlist=tdList[i].childNodes;
		var inputValue;
		var bl="false";
		for(var j=0;j<inputlist.length;j++){
			if(inputlist[j].tagName=="INPUT"){ 	
				inputValue=inputlist[j].value;
				bl="true";
			}else if(inputlist[j].tagName=="SELECT"){
			    for(var m=0;m<inputlist[j].options.length;m++)
					if(inputlist[j].options[m].value==inputlist[j].value){
						inputValue=inputlist[j].options[m].text;
						bl="true";
					}
			}
		}
		
		for(var j=0;j<inputlist.length;j++){
			if(bl=="true"&&inputlist[j].tagName=="LABEL"){ 
				inputlist[j].innerText=inputValue;
			}
		}
	}
};
//检查长度
function len(value){
	var str,Num = 0;
	for (var i=0;i<value.length;i++){
		str = value.substring(i,i+1);
		if (str<="~")  //判断是否双字节
			Num+=1;
		else
			Num+=2;
		}
	return Num;
};
//检查输入	
function checkInput(input){
	var altStr=input.alt;
	var inputValue=input.value;	
	var warnStr="";
	if(altStr=="")
		return true;
	if(altStr.indexOf("notnull")>=0){ /**非空判断*/
		if(inputValue==""||inputValue==null){
			warnStr += "【"+input.title+"】不能为空！";
		 	//alert("【"+input.title+"】不能为空！");
		 	//return false;
		 }
	}else if(inputValue==""){
		return true;
	}
	if(altStr.indexOf("int")>=0){ /**整数判断*/
		var isNum=!isNaN(inputValue);
		if(isNum)
			isNum=inputValue.indexOf(".")<0;
		if(!isNum){
			warnStr += "【"+input.title+"】应该输入整数！";
			//alert("【"+input.title+"】应该输入整数！");
			//return false;
		}	
	}
	if(altStr.indexOf("dec")>=0){ /**数字判断*/
		var isNum=!isNaN(inputValue);
		if(!isNum){
			warnStr += "【"+input.title+"】应该输入数字！";
			//alert("【"+input.title+"】应该输入数字！");
			//return false;
		}	
	}
	if(altStr.indexOf("char")>=0){/**长度判断*/
		var max=input.maxLength;				
		if(!isNaN(max)){
			var valueLen=len(inputValue);
			if(valueLen>parseInt(max)){
				warnStr += "【"+input.title+"】输入长度超过限制【"+valueLen+">"+max+"】！";
				//alert("【"+input.title+"】输入长度超过限制【"+valueLen+">"+max+"】！");
				//return false;
			}
		}
	}
	if(altStr.indexOf("tablename")>=0){ /**表名判断*/
		var patrn=/^[a-zA-Z][a-zA-Z0-9#_$]{0,39}$/;
		if(!patrn.exec(inputValue)) {
			warnStr += "【"+input.title+"】必须以字母开头，可包含数字或“# _ $”！";
		}
	}
	if(altStr.indexOf("trule")>=0){/**生成规则*/
		var pt = /^((#\[\w+\])?[a-zA-Z0-9_#$]*)*(#\{(YYYY([\+\-][0-9]{1,3})?)?((MM([\+\-][0-9]{1,2})?)?((DD([\+\-][0-9]{1,2})?)?)?)?\})?((#\[\w+\])?[a-zA-Z0-9_#$]*)*$/;
		if(!pt.exec(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请参考生成提示填写！";
		}
	}
	if(altStr.indexOf("tcycle")>=0){/**生成周期*/
		var pt = /^((yyyy)|([0-9]{4}))((MM)|([0-9]{2}))((dd)|([0-9]{2}))$/;
		if(!pt.exec(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请参考生成提示填写！";
		}
	}
	if(altStr.indexOf("tel")>=0){/**手机号码校验*/
		var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/; 
		if (!patrn.exec(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请输入合法的手机号码！";
		}
	}
	if(altStr.indexOf("email")>=0){/**邮箱校验*/
		var atIndex = inputValue.indexOf('@'); 
		var dotIndex = inputValue.indexOf('.', atIndex); 
		var flag = true; 
		var theSub = inputValue.substring(0, dotIndex+1);
		if ((atIndex < 1)||(atIndex != inputValue.lastIndexOf('@'))||(dotIndex < atIndex + 2)||(inputValue.length <= theSub.length)) {
			warnStr += "【"+input.title+"】格式不正确，请输入合法的邮箱地址！";
		}
	}
	if(altStr.indexOf("cron")>=0){/**cron表达式校验*/
		if(!cronValidate(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请输入合法的cron表达式！";
		}
	}
	return warnStr;
};

function _toArr(iterable){
  if (!iterable) return [];
  if (iterable.toArray) return iterable.toArray();
  var length = iterable.length, results = new Array(length);
  while (length--) results[length] = iterable[length];
  return results;
};
//---------------------
//保存数据
function save(form, isAjax){
	var params={}; 
	var inputError=false;
	//输入框  	
    var inputList = form.getElementsByTagName('*');
	var inputs = _toArr(inputList);
	var warnStr = "";
	for(var i=0; i<inputs.length;i++){
		var input = inputs[i];
		if(input.tagName=="INPUT"){ //输入框
			var s = checkInput(input);
			if(s.length >= 1) {
				warnStr+=(s + "\n");
			}
			/*if(input.type=="hidden")
				params["$"+input.name]=input.value;
			else*/
			params[input.name]=input.value;
		}else if(input.tagName=="SELECT"){ //选择框
			var sel = input.value;
			if(sel==""||sel==null){
				warnStr+=("【"+input.title + "】不能为空\n");
			}
			params[input.name]=input.value;
		}
	}
	//后台保存	
	if(warnStr.length >= 1) {
		alert(warnStr);		
		return;	
	}
	if(!window.confirm("确定保存吗？"))
		return;
	jQuery.ajax({
		type : 'post',
		url : _resourceForm.action,
		async:false,
		data : params,
		success : function(d) {
			if(d.flag==1){
				alert(d.msg);
			} else{
				setLabel(form);
				show(form,"","none");
				if(!isAjax)
					_queryForm.submit();
			}
		}
	});
//	new Ajax.Request(
//		_resourceForm.action,
//		{method:'post', parameters:params, onComplete:callbackFunc}
//	);
	//jQuery.post(_resourceForm.action, params, callbackFunc);
	//TableUpdater.update("${param.table}",params,callbackFunc);
};
//增加记录回调函数
function createCallback(o){
	var str = o.responseText;
	if(str.indexOf("失败")<0) {
		_queryForm.submit();
	} else {
		alert(str);
	}
};
//更新记录回调函数
function updateCallback(o){
	alert(o.flag);
	var str = o.responseText;
	if(str.indexOf("失败")>=1) {
		alert(str);
	}
};
//显示编辑行
function show(form,labelStyle,inputStyle){	
    var inputList = form.getElementsByTagName('*');
	var inputs = _toArr(inputList);
	for(var i=0;i<inputs.length;i++){
		var input = inputs[i];
		if((input.tagName=="INPUT"&&input.type=="text")||input.tagName=="SELECT") //输入框,选择框				
			input.style.display=inputStyle;
		else if((input.tagName=="INPUT"&&input.type=="chcek")||input.tagName=="LABEL") //选择框,标签
			input.style.display=labelStyle;	
		else if(input.tagName=="IMG"){ //按钮
			if(input.id=='imgEdit')
				input.style.display=labelStyle;
			else if(input.id=='imgSave')			
				input.style.display=inputStyle;
			else if(input.id=='imgCancel')			
				input.style.display=inputStyle;
		}
	}
};

//删除选择
function deleteSelected(t, action){
	var params = {};
	var selectCount=0;
	var no = "";
    for (i=0; i < ec_table[1].rows.length; i++) {
		var row=ec_table[1].rows[i];
		var keys="";
		var selected=false;
		var isCheck=false;
		var hasSub="false";
		var inputList = row.getElementsByTagName('*');
		var inputs = _toArr(inputList);
		for(var i=0;i<inputs.length;i++){
			var input = inputs[i];
			if(!input.tagName=='INPUT')
				return;
			if(input.type=='hidden') {
				if('db' == t && input.name == 'hasSub') {
					hasSub = input.value;
				}else {
					keys+=(keys!=""?"&":"")+input.name+"="+input.value;
				}
			}else if(input.type=="checkbox") {
				selected=input.checked;
			}
		}
		if(selected){
			if(hasSub == "true") {
				no += (no!=""?",":"")+(i+1);
			}
			selectCount++;
			params[(i+1)]=keys;
		}
    }
	if(selectCount<1){
    	alert("请首先选中要删除的记录！");
    	return;
    }
    if(no.length >= 1) {
		alert("第" + no + "条记录含有监控单元，请先将数据库下的监控单元删除");
		return;
	}
    var warn = "确定要删除?";
    if("cell" == t) {
    	warn = "警告!该操作将删除该监控单元下的所有子节点，确定要删除?";
    }
    if(!window.confirm(warn))
		return;
	new Ajax.Request(
		action,
		{method:'post', parameters:params, onComplete:deleteCallback}
	);
	//jQuery.post(action, {params : params}, deleteCallback);
    //TableUpdater.removeSelected("${param.table}",params,deleteCallback);
};
//删除数据回调函数
function deleteCallback(o){
	var str = o.responseText;
	alert(str);
	if(str.indexOf("错误")<0) {
		_queryForm.submit();
	} 
};
//改变显示块
function showDiv(addDivStyle,queryDivStyle){
	var qurrydiv = document.getElementById("qurrydiv");
	var addDiv = document.getElementById("addDiv");
	addDiv.style.display=addDivStyle;
	qurrydiv.style.display=queryDivStyle;		
};

function getNowFormatDate(){
   var day = new Date();
   var Year = 0;
   var Month = 0;
   var Day = 0;
   var CurrentDate = "";
   //初始化时间
   //Year= day.getYear();//有火狐下2008年显示108的bug
   Year= day.getFullYear();//ie火狐下都可以
   Month= day.getMonth()+1;
   Day = day.getDate(); 
   //Hour = day.getHours();
// Minute = day.getMinutes();
// Second = day.getSeconds();
   CurrentDate += Year + "-"; 
   if (Month >= 10 )
   {
    CurrentDate += Month + "-";
   }
   else
   {
    CurrentDate += "0" + Month + "-";
   }
   if (Day >= 10 )
   {
    CurrentDate += Day ;
   }
   else
   {
    CurrentDate += "0" + Day ;
   }
   return CurrentDate;
};
function getStart() {
	WdatePicker({maxDate:"#F{$dp.$D('endTime', {d:-1})||'" + getNowFormatDate() + "'}"});
};

function getEnd() {
	WdatePicker({minDate:"#F{$dp.$D('startTime', {d:1})||'" + getNowFormatDate() + "'}"});
};

function validateForm(){
	var warnStr = "";
	$(":input[alt]").each(function() {
		warnStr += checkAlt($(this));
	});
	if(warnStr.length>=1) {
		alert(warnStr);
		return false;
	}
	return true;
};
function checkAlt(input){
	var altStr=input.attr("alt");
	var inputValue=input.attr("value");	
	var warnStr="";
	if(altStr=="")
		return true;
	if(altStr.indexOf("notnull")>=0){ /**非空判断*/
		if(inputValue==""||inputValue==null){
			warnStr += "【"+input.attr("title")+"】不能为空！";
		 }
	}
	if(altStr.indexOf("int")>=0){ /**整数判断*/
		var isNum=!isNaN(inputValue);
		if(isNum)
			isNum=inputValue.indexOf(".")<0;
		if(!isNum){
			warnStr += "【"+input.title+"】应该输入整数！";
		}	
	}
	if(altStr.indexOf("dec")>=0){ /**数字判断*/
		var isNum=!isNaN(inputValue);
		if(!isNum){
			warnStr += "【"+input.title+"】应该输入数字！";
		}	
	}
	if(altStr.indexOf("img")>=0){ /**数字判断*/
		if(inputValue!=""&&inputValue!=null){
			var fileType=inputValue.substring(inputValue.lastIndexOf('.'),inputValue.lenth).toLowerCase();
			if(fileType!='.bmp' && fileType!='.gif' && fileType!='.jpeg' && fileType!='.png' && fileType!='.jpg'){
				warnStr += "【"+input.title+"】必须是bmp,gif,jpeg,jpg,png中的一种！";
			}
		 }
	}
	if(altStr.indexOf("char")>=0){/**长度判断*/
		var max=input.maxLength;				
		if(!isNaN(max)){
			var valueLen=len(inputValue);
			if(valueLen>parseInt(max)){
				warnStr += "【"+input.title+"】输入长度超过限制【"+valueLen+">"+max+"】！";
			}
		}
	}
	if(altStr.indexOf("tablename")>=0){ /**表名判断*/
		var patrn=/^[a-zA-Z][a-zA-Z0-9#_$]{0,39}$/;
		if(!patrn.exec(inputValue)) {
			warnStr += "【"+input.title+"】必须以字母开头，可包含数字或“# _ $”！";
		}
	}
	if(altStr.indexOf("trule")>=0){/**生成规则*/
		var pt = /^((#\[\w+\])?[a-zA-Z0-9_#$]*)*(#\{(YYYY([\+\-][0-9]{1,3})?)?((MM([\+\-][0-9]{1,2})?)?((DD([\+\-][0-9]{1,2})?)?)?)?\})?((#\[\w+\])?[a-zA-Z0-9_#$]*)*$/;
		if(!pt.exec(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请参考生成提示填写！";
		}
	}
	if(altStr.indexOf("tcycle")>=0){/**生成周期*/
		var pt = /^((yyyy)|([0-9]{4}))((MM)|([0-9]{2}))((dd)|([0-9]{2}))$/;
		if(!pt.exec(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请参考生成提示填写！";
		}
	}
	if(altStr.indexOf("tel")>=0){/**手机号码校验*/
		var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/; 
		if (!patrn.exec(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请输入合法的手机号码！";
		}
	}
	if(altStr.indexOf("email")>=0){/**邮箱校验*/
		var atIndex = inputValue.indexOf('@'); 
		var dotIndex = inputValue.indexOf('.', atIndex); 
		var flag = true; 
		var theSub = inputValue.substring(0, dotIndex+1);
		if ((atIndex < 1)||(atIndex != inputValue.lastIndexOf('@'))||(dotIndex < atIndex + 2)||(inputValue.length <= theSub.length)) {
			warnStr += "【"+input.title+"】格式不正确，请输入合法的邮箱地址！";
		}
	}
	if(altStr.indexOf("cron")>=0){/**cron表达式校验*/
		if(!cronValidate(inputValue)) {
			warnStr += "【"+input.title+"】格式不正确，请输入合法的cron表达式！";
		}
	}
	return warnStr;
};

String.prototype.trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.endWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
		return false;
	if(this.substring(this.length-str.length)==str)
		return true;
	else
		return false;
};