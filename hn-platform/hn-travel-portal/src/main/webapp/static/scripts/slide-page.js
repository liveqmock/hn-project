/**
 * 幻灯片栏效果（菜单、焦点图）
 */
function slideAnimate(menuItem, childMenu, slideBox){
	//左侧菜单
	$(menuItem).hover(function(){
		$(this).addClass('over');
		$(this).children(childMenu).show('fast');
	}, function(){
		$(this).removeClass('over');
		$(this).children(childMenu).hide();
	});
	//中间焦点图
	var focusImg = $(slideBox), list = focusImg.children('ul.list'), tab = focusImg.children('ul.tab');
	var fli = list.children(':first'), fw = fli.outerWidth(), tabA = tab.find('li>a');
	var next = 1, l = tabA.length;
	list.append(fli.clone()).width((l + 1) * fw);
	
	var am = function(){
		tabA.removeClass('over');
		var i = next;
		if(next >= l){
			next = l;
			i = 0;
		}
		tabA.filter(':eq(' + i +')').addClass('over');
		list.animate({
			marginLeft: -fw * next
		}, function(){
			if(next == l)
				list.css('margin-left', 0);
			next++;
			if(next > l)
				next = 1;
			toint = setTimeout(am, 5000);
		});
	};
	var toint = setTimeout(am, 5000);
	
	tabA.mouseenter(function(){
		list.stop(true, true);
		clearTimeout(toint);
		next = $(this).parent().index();
		am();
	});
}

/**
 * 翻页
 */
function pageAnimate(prevBt, nextBt, scrollCon, scrollChild, overFn){
	var prev = $(prevBt), next = $(nextBt);
	var ul = $(scrollCon), li = ul.children(scrollChild), liw = li.outerWidth(), ulw = liw * li.length;
	ul.width(ulw);
	ulw = -ulw + liw;
	
	var checkOver = function(){
		prev.addClass('over');
		next.addClass('over');
		var curm = parseInt(ul.css('margin-left'));
		if(curm >= 0){
			prev.removeClass('over');
			ul.css('margin-left', curm = 0);
		}else if(curm <= ulw){
			next.removeClass('over');
			ul.css('margin-left', curm = ulw);
		}
		
		if(overFn)
			overFn(-curm / liw);
	};
	checkOver();
	
	var am = function(b){
		if(!$(this).hasClass('over'))
			return;
		ul.animate({
			marginLeft: (b ? '+=' : '-=') + liw
		}, function(){
			checkOver();
		});
	};
	prev.click(function(){
		am.call(this, true);
	});
	next.click(function(){
		am.call(this, false);
	});
}