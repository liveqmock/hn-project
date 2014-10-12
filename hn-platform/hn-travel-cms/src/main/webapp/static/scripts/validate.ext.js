jQuery.validator.addMethod("chrnum", function(value, element) {
	var chrnum = /^([a-zA-Z0-9]+)$/;
	return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

jQuery.validator.addMethod("mobile", function(value, element) {
	var length = value.length;
	var mobile = /^(1\d{10})$/;
	return this.optional(element) || mobile.test(value);
}, "手机号码格式错误");

jQuery.validator.addMethod("tel", function(value, element) {
	var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

jQuery.validator.addMethod("numeric", function(value, element, param) {
	if(this.optional(element))
		return true;
	if ( typeof param === "string" ) {
		var parts = param.replace(/[\[\]]/g, "" ).split( /[\s,]+/ );
		param = [ Number( parts[ 0 ]), Number( parts[ 1 ] ) ];
	}
	var numeric = /^(-?(([1-9]\d*)|0)(\.\d+)?)$/;
	if(numeric.test(value)){
		var re = value.replace('-', '').split('.');
		if(re[0].length < param[0]){
			if(re.length == 2)
				return re[1] < param[1];
			else
				return true;
		}
	}
	return false;
}, $.validator.format("请输入小数，整数 {0} 位，小数 {1} 位"));

jQuery.validator.addMethod("idCard", function(value, element) {
	return this.optional(element) || __isIdCardNo(value);
}, "请正确输入您的身份证号码");

// 增加身份证验证
function __isIdCardNo(num) {
	var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
			2, 1);
	var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3",
			"2");
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		return false;
	}
	// check and set value
	for (i = 0; i < intStrLen; i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	if (intStrLen == 18) {
		// check date
		var date8 = idNumber.substring(6, 14);
		if (__isDate8(date8) == false) {
			return false;
		}
		// calculate the sum of the products
		for (i = 0; i < 17; i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = parityBit[lngProduct % 11];
		// check last digit
		if (varArray[17] != intCheckDigit) {
			return false;
		}
	} else { // length is 15
		// check date
		var date6 = idNumber.substring(6, 12);
		if (__isDate6(date6) == false) {
			return false;
		}
	}
	return true;
}
function __isDate6(sDate) {
	if (!/^[0-9]{6}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	if (year < 1700 || year > 2500)
		return false
	if (month < 1 || month > 12)
		return false
	return true
}
/**
 * 判断是否为“YYYYMMDD”式的时期
 * 
 */
function __isDate8(sDate) {
	if (!/^[0-9]{8}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	day = sDate.substring(6, 8);
	var iaMonthDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ]
	if (year < 1700 || year > 2500)
		return false
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		iaMonthDays[1] = 29;
	if (month < 1 || month > 12)
		return false
	if (day < 1 || day > iaMonthDays[month - 1])
		return false
	return true
}