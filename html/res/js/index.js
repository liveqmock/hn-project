$(function(){
	slideBarEffect();
	hotlineSale();
	hotspotPage();
	hotspotSale();
	polymerEffect();
	imgHoverAmp();
	imgHoverOpacity();
	headShow();
	windowScroll();
});

/**
 * 幻灯片栏效果（菜单、焦点图）
 */
function slideBarEffect(){
	slideAnimate('#SlideDiv>.mainbox>.sl>ul>li', '.mc', '#SlideDiv>.mainbox>.sc');
}

/**
 * 热门线路热销Tab切换
 */
function hotlineSale(){
	var div = $('#HotlineSale'), dda = div.find('dl>dd>a');
	var uls = div.find('.rb>ul');
	dda.mouseenter(function(){
		dda.removeClass('over');
		$(this).addClass('over');
		uls.hide();
		$(uls[dda.index(this)]).show();
	});
}

/**
 * 热门景点翻页
 */
function hotspotPage(){
	pageAnimate('#HotspotPage>.page:first', '#HotspotPage>.page:last', '#HotspotPage>.con>ul', 'li');
}

/**
 * 热门景点促销效果
 */
function hotspotSale(){
	$('#HotspotSale>li').hover(function(){
		$(this).animate({
			paddingLeft: 0,
			paddingRight: 0
		}, 'fast');
	}, function(){
		$(this).css({
			paddingLeft: 5,
			paddingRight: 5
		});
	});
}

/**
 * 聚合图片效果
 */
function polymerEffect(){
	var lis = $('#PolymerBar li:not(.nom)'), mks = lis.find('.mk'), mts = lis.find('.mt');
	lis.hover(function(){
		mts.hide();
		mks.css('opacity', 0);
		var t = $(this), mk = t.find('.mk'), mt = t.find('.mt');
		mk.animate({
			opacity: 0.6
		}, 'fast', function(){
			mt.show();
		});
	}, function(){
		var t = $(this);
		t.find('.mt').hide();
		t.find('.mk').css('opacity', 0);
	}).find('.mk').css('opacity', 0).show();
}

/**
 * 鼠标移到图片上时的放大效果
 */
function imgHoverAmp(){
	var amp = 10, m = amp / 2;
	$('img[hoverAmp]').hover(function(){
		$(this).animate({
			width: '+=' + amp,
			height: '+=' + amp,
			marginLeft: '-=' + m,
			marginTop: '-=' + m
		}, 'fast');
	}, function(){
		$(this).animate({
			width: '-=' + amp,
			height: '-=' + amp,
			marginLeft: '+=' + m,
			marginTop: '+=' + m
		}, 'fast');
	});
}

/**
 * 鼠标移到图片上时的透明度变化
 */
function imgHoverOpacity(){
	$('img[hoverOpacity]').hover(function(){
		$(this).animate({
			opacity: ($(this).attr('hoverOpacity') == 1 ? 0.6 : 1)
		}, 'fast');
	}, function(){
		$(this).animate({
			opacity: ($(this).attr('hoverOpacity') == 1 ? 1 : 0.6)
		}, 'fast');
	}).css('opacity', 0.6).filter('[hoverOpacity="1"]').css('opacity', 1);
}
