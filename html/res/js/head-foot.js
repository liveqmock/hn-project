/**
 * 头部一些下拉显示效果
 */
function headShow(){
	//已登录下拉框
	var login = $('#HeadTop>.con>.login'), l_name = login.children('.name'), l_ul = login.children('ul');
	login.hover(function(){
		l_name.addClass('over');
		l_ul.slideDown('fast');
	}, function(){
		l_name.removeClass('over');
		l_ul.slideUp('fast');
	});
	
	//搜索下拉框
	var search = $('#HeadTop>.con>.search'), s_si = search.children('.si'), s_ss = search.children('.ss');
	search.hover(function(){
		s_si.addClass('over');
		s_ss.animate({height: 'show'}, 'fast');
	}, function(){
		s_si.removeClass('over');
		s_ss.hide();
	});
	
	//搜索类型选项下拉框
	var ssl = s_ss.children('.ssl'), ssl_ul = ssl.children('ul');
	ssl.hover(function(){
		ssl_ul.slideDown('fast');
	}, function(){
		ssl_ul.slideUp('fast');
	});
	
	//搜索类型选项下拉框选择
	var ssl_li = ssl_ul.find('li'), ssl_ts = ssl.find('.sslt>span');
	ssl_li.click(function(){
		ssl_ts.text($(this).text());
		ssl_li.show();
		$(this).hide();
	});
}

/**
 * 窗口滚动时的一些效果（小导航栏、背景、回到顶部）
 */
function windowScroll(){
	var body = $('body'), tip = $('#FootTip'), headBox = $('#HeadBox'), ht = headBox.children('.headtop');
	var logo = ht.find('.logo'), menu = ht.find('.menu'), search = ht.find('.search');
	var hlogo = headBox.children('.headlogo'), hnav = headBox.children('.headnav'), hsearch = headBox.children('.headsearch');
	var mainTop = 392, logoTop = 260, menuTop = 180, searchTop = 90;
	
	//头部logo,menu,search显示隐藏
	var headFn = function(b, obj, obj2){
		if(b){
			obj2.slideUp('fast');
			obj.show('fast');
		}else{
			obj2.slideDown('fast');
			obj.hide('fast');
		}
	};
	//背景跟随滚动、定住
	var bodyBgFn = function(b){
		body.css({
			'background-attachment': b ? 'scroll' : 'fixed',
			'background-position': b ? 'center 428px' : 'center 0'
		});
	};
	//回到顶部显示、隐藏
	var tipFn = function(b){
		tip.animate({
			bottom: b ? 60 : 0,
			opacity: b ? 'show' : 'hide'
		});
	};
	
	//滚动时如果条件满足执行相应的函数
	var cbFn = function(fn, b1, b2, obj, obj2){
		if((b1 && b2) || (!b1 && !b2))
			fn(!b1, obj, obj2);
	};
	$(window).scroll(function(){
		var top = $(this).scrollTop(), ltMainTop = top < mainTop;
		cbFn(bodyBgFn, ltMainTop, 'fixed' != body.css('background-attachment'));
		cbFn(tipFn, ltMainTop, tip.is(':visible'));
		cbFn(headFn, top < logoTop, logo.is(':visible'), logo, hlogo);
		cbFn(headFn, top < menuTop, menu.is(':visible'), menu, hnav);
		cbFn(headFn, top < searchTop, search.is(':visible'), search, hsearch);
	});
	
	//回到顶部
	tip.children('.gotop').click(function(){
		$(window).scrollTop(0);
	});
}