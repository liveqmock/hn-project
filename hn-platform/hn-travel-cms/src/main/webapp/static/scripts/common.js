
$.ajaxSetup({
	traditional: true
});

function processResult(data, redirectUrl, successMsg, failMsg){
	if(data && data.success){
		if(successMsg)
			alert(successMsg);
		if(redirectUrl)
			location.href = redirectUrl;
	}else if(data && data.error)
		alert(data.error);
	else if(failMsg)
		alert(failMsg);
}

function submitForm(form, redirectUrl, successMsg, failMsg, onReturn) {
	if($(form).data('saveing')){
		alert('提交处理中...');
		return;
	}
	$(form).data('saveing', true);	
	
	$(form).ajaxSubmit({
		dataType : 'json',
		success : function(data) {
			if (onReturn)
				onReturn(data, form);
			processResult(data, redirectUrl, successMsg, failMsg);
		},
		complete : function() {
			$(form).removeData('saveing');
		}
	});
}

function delConfirm(id, delUrl, unSelMsg, redirectUrl, cfmMsg, successMsg, failMsg, cbName, onReturn){
	var ids = [];
	if(id){
		ids.push(id);
	}else{
		cbName = cbName || 'selBox';
		$(':checkbox[name="' + cbName + '"]:checked').each(function(){
			ids.push(this.value);
		});
	}
	if(ids.length){
		if(confirm(cfmMsg || '是否确定删除？')){
			$.get(delUrl, {ids : ids}, function(data){
				if (onReturn)
					onReturn(data);
				processResult(data, redirectUrl, successMsg || '执行删除成功', failMsg || '执行删除失败');
			});
		}
	}else{
		alert(unSelMsg || '请至少选择一条记录');
	}
}