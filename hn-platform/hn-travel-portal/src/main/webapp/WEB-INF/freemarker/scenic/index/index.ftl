<!doctype html>
<html>
<head>
<#include "/commons/_inc_res.ftl" />
<link href="/static/styles/scenic.css" rel="stylesheet" type="text/css" />
<script src="/static/scripts/slide-page.js" type="text/javascript"></script>
<title>海南旅游网 - 景点门票首页</title>
<script type="text/javascript">
$(function(){
	headShow();
	
	slideAnimate('#SlideDiv>.mainbox>.left>.menubox>.box', '.child', '#SlideDiv>.mainbox>.right');
	pageAnimate('#HotseasonPage>.sc>.prev', '#HotseasonPage>.sc>.next', '#HotseasonPage>.sc>.scc', 'ul', function(ind){
		var lis = $('#HotseasonPage>.st>ul>li').removeClass('over');
		$(lis[ind]).addClass('over');
	});
	recScenicEffect();
});

/**
 * 推荐景点效果
 */
function recScenicEffect(){
	$('#RecScenic>.con>ul>li').hover(function(){
		var rea = $(this).find('.am>.rea').show();
		rea.children('div').animate({
			opacity: 0.9
		});
	}, function(){
		var rea = $(this).find('.am>.rea')
		rea.children('div').animate({
			opacity: 0
		}, 'fast', function(){
			rea.hide();
		});
	}).find('.am>.rea>div').css('opacity', 0);
}
</script>
</head>

<body class="body-bg scroll-body-bg">
<!-- 通用页头 开始 -->
<#include "/commons/_inc_glob_head.ftl" />
<!-- 通用页头 结束 -->


<!-- 幻灯片通栏 开始 -->
<div id="SlideDiv" class="slide-bar slide-scenic">
 <div class="shade"><p><i></i></p></div>
 <div class="mainbox">
  <div class="left">
   <div class="search">
    <b>海南</b>
    <a href="#" class="prec">精确查找</a>
    <form>
     <input type="text" placeholder="景点/目的地/主题" />
     <a href="#"></a>
    </form>
   </div>
   <ul class="menubox">
    <li class="box">
     <div class="menu">
      <p>主题特色</p>
      <a href="#">亚龙湾热带天堂森林公园</a>
      <a href="#">呀诺达</a>
      <a href="#">三亚千古情</a>
     </div>
     <i></i>
     <div class="child">
      <dl>
       <dt>最热门的景点</dt>
       <dd><a href="#">亚龙湾热带天堂森林公园</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">三亚千古情</a></dd>
       <dd><a href="#">蜈支洲岛</a></dd>
       <dd><a href="#">西岛</a></dd>
       <dd><a href="#">分界洲岛</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">槟榔谷</a></dd>
       <dd><a href="#">天涯海角</a></dd>
       <dd><a href="#">三亚大小洞天门票</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">大小洞天</a></dd>
       <dd><a href="#" class="more">更多&gt;&gt;</a></dd>
      </dl>
      <h3>热销门票</h3>
      <div class="cl">
       <a href="#"><img src="/static/p/hl11.jpg" /></a>
       <a href="#" class="clt">蜈支洲岛</a>
       <p>触手可及的"马尔代夫"<br/>中国第一潜水基地<br/>龙湾美景身后静静绽放光彩的度假天堂</p>
      </div>
      <ul class="cr">
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li><a href="#">【全网超低价】福建武夷山景区三联票</a></li>
       <li><a href="#">厦门观音山梦幻水陆世界</a></li>
       <li><a href="#">厦门方特梦幻王国（直通车）</a></li>
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li class="more"><a href="#">更多&gt;&gt;</a></li>
      </ul>
     </div>
    </li>
    <li class="box">
     <div class="menu">
      <p>大沙滩</p>
      <a href="#">蜈支洲岛</a>
      <a href="#">西岛</a>
      <a href="#">分界洲岛</a>
      <a href="#">呀诺达</a>
     </div>
     <i></i>
     <div class="child c2">
      <dl>
       <dt>最热门的景点</dt>
       <dd><a href="#">亚龙湾热带天堂森林公园</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">三亚千古情</a></dd>
       <dd><a href="#">蜈支洲岛</a></dd>
       <dd><a href="#">西岛</a></dd>
       <dd><a href="#">分界洲岛</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">槟榔谷</a></dd>
       <dd><a href="#">天涯海角</a></dd>
       <dd><a href="#">三亚大小洞天门票</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">大小洞天</a></dd>
       <dd><a href="#" class="more">更多&gt;&gt;</a></dd>
      </dl>
      <h3>热销门票</h3>
      <div class="cl">
       <a href="#"><img src="/static/p/hl11.jpg" /></a>
       <a href="#" class="clt">蜈支洲岛</a>
       <p>触手可及的"马尔代夫"<br/>中国第一潜水基地<br/>龙湾美景身后静静绽放光彩的度假天堂</p>
      </div>
      <ul class="cr">
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li><a href="#">【全网超低价】福建武夷山景区三联票</a></li>
       <li><a href="#">厦门观音山梦幻水陆世界</a></li>
       <li><a href="#">厦门方特梦幻王国（直通车）</a></li>
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li class="more"><a href="#">更多&gt;&gt;</a></li>
      </ul>
     </div>
    </li>
    <li class="box">
     <div class="menu">
      <p>水上乐园</p>
      <a href="#">南山寺</a>
      <a href="#">槟榔谷</a>
      <a href="#">天涯海角</a>
      <a href="#">三亚大小洞天门票</a>
     </div>
     <i></i>
     <div class="child c3">
      <dl>
       <dt>最热门的景点</dt>
       <dd><a href="#">亚龙湾热带天堂森林公园</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">三亚千古情</a></dd>
       <dd><a href="#">蜈支洲岛</a></dd>
       <dd><a href="#">西岛</a></dd>
       <dd><a href="#">分界洲岛</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">槟榔谷</a></dd>
       <dd><a href="#">天涯海角</a></dd>
       <dd><a href="#">三亚大小洞天门票</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">大小洞天</a></dd>
       <dd><a href="#" class="more">更多&gt;&gt;</a></dd>
      </dl>
      <h3>热销门票</h3>
      <div class="cl">
       <a href="#"><img src="/static/p/hl11.jpg" /></a>
       <a href="#" class="clt">蜈支洲岛</a>
       <p>触手可及的"马尔代夫"<br/>中国第一潜水基地<br/>龙湾美景身后静静绽放光彩的度假天堂</p>
      </div>
      <ul class="cr">
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li><a href="#">【全网超低价】福建武夷山景区三联票</a></li>
       <li><a href="#">厦门观音山梦幻水陆世界</a></li>
       <li><a href="#">厦门方特梦幻王国（直通车）</a></li>
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li class="more"><a href="#">更多&gt;&gt;</a></li>
      </ul>
     </div>
    </li>
    <li class="box">
     <div class="menu">
      <p>套餐推荐</p>
      <a href="#">南山寺</a>
      <a href="#">大小洞天</a>
      <a href="#">槟榔谷</a>
     </div>
     <i></i>
     <div class="child c4">
      <dl>
       <dt>最热门的景点</dt>
       <dd><a href="#">亚龙湾热带天堂森林公园</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">三亚千古情</a></dd>
       <dd><a href="#">蜈支洲岛</a></dd>
       <dd><a href="#">西岛</a></dd>
       <dd><a href="#">分界洲岛</a></dd>
       <dd><a href="#">呀诺达</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">槟榔谷</a></dd>
       <dd><a href="#">天涯海角</a></dd>
       <dd><a href="#">三亚大小洞天门票</a></dd>
       <dd><a href="#">南山寺</a></dd>
       <dd><a href="#">大小洞天</a></dd>
       <dd><a href="#" class="more">更多&gt;&gt;</a></dd>
      </dl>
      <h3>热销门票</h3>
      <div class="cl">
       <a href="#"><img src="/static/p/hl11.jpg" /></a>
       <a href="#" class="clt">蜈支洲岛</a>
       <p>触手可及的"马尔代夫"<br/>中国第一潜水基地<br/>龙湾美景身后静静绽放光彩的度假天堂</p>
      </div>
      <ul class="cr">
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li><a href="#">【全网超低价】福建武夷山景区三联票</a></li>
       <li><a href="#">厦门观音山梦幻水陆世界</a></li>
       <li><a href="#">厦门方特梦幻王国（直通车）</a></li>
       <li><a href="#">【下单立减10元】中国福州云顶景区</a></li>
       <li class="more"><a href="#">更多&gt;&gt;</a></li>
      </ul>
     </div>
    </li>
   </ul>
  </div>
  
  <div class="right">
   <ul class="list">
    <li><a href="#"><img src="/static/p/slide1.jpg" /></a></li>
    <li><a href="#"><img src="/static/p/slide2.jpg" /></a></li>
    <li><a href="#"><img src="/static/p/slide3.jpg" /></a></li>
    <li><a href="#"><img src="/static/p/slide1.jpg" /></a></li>
    <li><a href="#"><img src="/static/p/slide2.jpg" /></a></li>
    <li><a href="#"><img src="/static/p/slide3.jpg" /></a></li>
   </ul>
   <ul class="tab">
    <li class="f"><a href="#" class="over">三亚风情等你来！</a></li>
    <li><a href="#">0元旅游不是梦！</a></li>
    <li><a href="#">哈哈，国庆又来啦</a></li>
    <li><a href="#">准备好出发了吗？</a></li>
    <li><a href="#">你说去哪里</a></li>
    <li><a href="#">三亚风情等你来！</a></li>
   </ul>
  </div>
 </div>
</div>
<!-- 幻灯片通栏 结束 -->

<!-- 当季热卖 开始 -->
<div id="HotseasonPage" class="mainbox hotseason-bar">
 <h2>当季热卖<span>Hot!</span></h2>
 <div class="sc">
  <div class="scc">
   <ul>
    <li>
     <a href="/scenic/detail/1.htm"><img src="/static/p/hl11.jpg"/></a>
     <p class="t ellipsis"><a href="/scenic/detail/1.htm">珠江夜游</a></p>
     <p class="b"><span><i>￥43</i></span><del>原价￥65</del><a href="/scenic/detail/1.htm">去看看</a></p>
    </li>
    <li>
     <a href="/scenic/detail/2.htm"><img src="/static/p/hl12.jpg"/></a>
     <p class="t ellipsis"><a href="/scenic/detail/2.htm">兰花世界</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
    <li>
     <a href="/scenic/detail/2.htm"><img src="/static/p/hl13.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="/scenic/detail/2.htm">去看看</a></p>
    </li>
    <li>
     <a href="#"><img src="/static/p/hl14.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
   </ul>
   
   <ul>
    <li>
     <a href="#"><img src="/static/p/hr11.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
    <li>
     <a href="#"><img src="/static/p/hr12.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
    <li>
     <a href="#"><img src="/static/p/hr13.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
    <li>
     <a href="#"><img src="/static/p/hr14.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
   </ul>
   
   <ul>
    <li>
     <a href="#"><img src="/static/p/po1.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
    <li>
     <a href="#"><img src="/static/p/po2.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
    <li>
     <a href="#"><img src="/static/p/po3.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
    <li>
     <a href="#"><img src="/static/p/po4.jpg"/></a>
     <p class="t ellipsis"><a href="#">风情蜈支洲岛</a></p>
     <p class="b"><span><i>￥</i>65</span><del>原价￥89</del><a href="#">去看看</a></p>
    </li>
   </ul>
  </div>
  <a href="javascript:void(0)" class="prev"></a>
  <a href="javascript:void(0)" class="next"></a>
 </div>
 <div class="st">
  <ul><li class="over"></li><li></li><li></li></ul>
 </div>
</div>
<!-- 当季热卖 结束 -->

<!-- 推荐景点 开始 -->
<div id="RecScenic" class="mainbox recscenic-bar">
 <div class="tit">
  <p>推荐景点</p>
  <ul>
   <li><a href="#" class="over">三亚</a></li>
   <li><a href="#">海口</a></li>
   <li><a href="#">西岛</a></li>
   <li><a href="#">保亭</a></li>
   <li><a href="#">万宁</a></li>
  </ul>
 </div>
 <div class="con">
  <ul>
   <li>
    <a href="#" class="am">
     <img src="/static/p/hs2.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
   <li>
    <a href="#" class="am">
     <img src="/static/p/ce1.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
   <li>
    <a href="#" class="am">
     <img src="/static/p/hs4.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
   <li>
    <a href="#" class="am">
     <img src="/static/p/hs5.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
   <li>
    <a href="#" class="am">
     <img src="/static/p/hs6.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
   <li>
    <a href="#" class="am">
     <img src="/static/p/hs7.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
   <li>
    <a href="#" class="am">
     <img src="/static/p/ce3.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
   <li>
    <a href="#" class="am">
     <img src="/static/p/ce2.jpg" />
     <div class="rea">
      <div></div>
      <p><b>推荐理由：</b>多样化的娱乐设施,获众多国际大奖的顶尖项目，快来让快乐和刺激持续升级吧!</p>
     </div>
    </a>
    <div class="tb"><a href="#">南山佛教文化苑<i>&nbsp;</i></a></div>
   </li>
  </ul>
  <div class="cr">
   <a href="#"><img src="/static/p/rs1.jpg" /></a>
   <a href="#" class="more">更多<span>【三亚】</span>门票<i>&nbsp;</i></a>
  </div>
 </div>
</div>
<!-- 推荐景点 结束 -->


<#include "/commons/_inc_foot.ftl" />
</body>
</html>
