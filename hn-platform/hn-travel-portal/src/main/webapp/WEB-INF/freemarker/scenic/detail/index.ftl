<!doctype html>
<html>
<head>
<title>海南旅游网 - 景点门票详情</title>
<#include "/commons/_inc_res.ftl" />
<link href="/static/styles/ticket.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
	headShow();
	
	//票据说明展开、关闭效果
	$('#TicketBook table a.name').click(function(){
		$(this).closest('table').next('.tip').slideToggle('fast');
	});
	
	//评论回复展开、关闭效果
	$('#Comment .from>.re').click(function(){
		$(this).parent().next('form').slideToggle('fast');
	});
	
	//详情导航效果
	var tab = $('#TicketTab'), tabli = tab.find('li'), tabTop = tab.offset().top;
	var dest = $('.ticket-dest'), notice = dest.find('a[name="notice"]');
	var introduce = dest.find('a[name="introduce"]'), review = dest.find('a[name="review"]');
	$(window).scroll(function(){
		var noticeTop = notice.offset().top - 61, introduceTop = introduce.offset().top - 61, reviewTop = review.offset().top - 61;
		var top = $(this).scrollTop();
		if(top > tabTop){
			$('#TicketTab').addClass('fixed');
			tabli.removeClass('over');
			if(top > reviewTop){
				tabli.has('a[href="#review"]').addClass('over');
			}else if(top > introduceTop){
				tabli.has('a[href="#introduce"]').addClass('over');
			}else if(top > noticeTop){
				tabli.has('a[href="#notice"]').addClass('over');
			}else{
				tabli.has('a[href="#book"]').addClass('over');
			}
		}else{
			$('#TicketTab').removeClass('fixed');
			tabli.removeClass('over');
			tabli.has('a[href="#book"]').addClass('over');
		}
	});
});
</script>
</head>

<body class="body-bg scroll-body-bg">
<!-- 通用页头 开始 -->
<#include "/commons/_inc_glob_head.ftl" />
<!-- 通用页头 结束 -->


<!-- 面包屑导航 开始 -->
<div class="slide-bar ticket-crumbs">
 <div class="shade"><p><i></i></p></div>
 <div class="mainbox">
  <div class="crumbs">您所在的位置：<a href="/index.htm">首页</a> &gt; 
   <a href="/scenic/">景点门票</a> &gt; 
   <a href="#">海南</a> &gt; 
   <span>${scenic.title!""}</span>
  </div>
 </div>
</div>
<!-- 面包屑导航 结束 -->

<!-- 概要信息 开始 -->
<div class="mainbox ticket-general">
 <h1>
  <span class="tit">${scenic.name!""}</span>
  <a href="#">立即预定</a>
  <span class="price">￥<b>${(scenic.lowPrice?c)!""}</b>起</span>
 </h1>
 <div class="con">
  <div class="cl">
  <img src="${(scenic.imgUri)!""}" width="702" height="269" />
   <!--img src="/static/p/ce1.jpg" width="506" height="269" />
   <img src="/static/p/ce2.jpg" width="189" height="131" />
   <img src="/static/p/ce3.jpg" width="189" height="131" / -->
  </div>
  <div class="cr">
   <div class="box">
    <a href="#" class="fav"><i></i>收藏</a>
    <ul>
     <li><a href="#">地方大</a></li>
     <li><a href="#">有美食</a></li>
     <li><a href="#">比基尼海滩</a></li>
    </ul>
    <div class="grade">
     <b>综合评分：</b>
     <span>4.7分</span>
     <a href="#">（278人参与评价）</a>
    </div>
    <p><i><b></b></i>一个充满“2”的地方。另外参加了踏瀑戏水拓展项目（90/人），穿着草鞋带上安全帽，在小溪里踩着各种石头艰难前行、过独木桥、拉着绳索爬上瀑布等等，很累但是很开心</p>
   </div>
   <p class="line"><b>具体地址：</b>${(scenic.address)!""}</p>
   <p class="line"><b>开放时间：</b>${(scenic.inTime)!""}</p>
   <div class="srv">
    <a href="#">入园保证</a>
    <a href="#" class="s2">随时退</a>
    <a href="#" class="s3">贵就赔</a>
   </div>
  </div>
 </div>
</div>
<!-- 概要信息 结束 -->

<!-- Tab切换 结束 -->
<a name="book"></a>
<div class="mainbox ticket-tab">
 <div id="TicketTab">
  <ul>
   <li class="over"><a href="#book">门票预订</a></li>
   <li><a href="#notice" class="t2">预订须知</a></li>
   <li><a href="#introduce" class="t3">景点介绍</a></li>
   <li class="pl"><a href="#review" class="t4">用户点评<span>（278人评论）</span></a></li>
  </ul>
 </div>
</div>
<!-- Tab切换 结束 -->

<!-- 门票预订 结束 -->
<div id="TicketBook" class="mainbox ticket-book">
 <div class="item">
  <dl>
   <dt>单门票</dt>
   <dd>
    <table><tr>
     <td width="490">
      <a href="javascript:void(0)" class="name">亚龙湾热带天堂森林公园门票</a>
      <a href="#" class="tip">点评奖金</a>
     </td>
     <td width="396"><b>￥128</b><span>（门店价：235）</span></td>
     <td width="210">在线支付</td>
     <td><a href="#" class="book">预 订</a></td>
    </tr></table>
    <div class="tip">
     <div class="tt"></div>
     <div class="con">
      <!-- 后台读取的内容 开始 -->
      <p>1、费用包含：森波拉奇妙世界主题乐园观光门票（不含机动游戏值）+冰川水谷乐园门票 1张；</p>
      <p>2、各园区营业时间：奇妙世界：09：00--18：00（17：00停止换票）；冰川水谷：10：00--23：00；</p>
      <p>3、景区地址：广东省清远市佛冈县羊角山森波拉度假森林内；</p>
      <table style="margin-top:10px"><tr>
       <td width="32" valign="top" style="padding-top:5px"><img src="/static/p/xl.jpg" /></td>
       <td style="color:#e8e8e8">备注：a、此套票奇妙世界门票为观光票不含机动游戏值，如客人需要游玩景区内其他园中园收费项目,费用请游客自理； b、冰川水谷景区内如储物柜、泳圈等需收取相应的租用费用，如客人需使用请自理费用！c、本产品不含旅游人身意外险，我们强烈建议游客购买。游客可在填写订单时勾选附件产品中的相关保险购买；</td>
      </tr></table>
      <!-- 后台读取的内容 结束 -->
     </div>
    </div>
   </dd>
   <dd>
    <table><tr>
     <td width="490">
      <a href="javascript:void(0)" class="name">亚龙湾热带天堂森林公园门票</a>
     </td>
     <td width="396"><b>￥128</b><span>（门店价：235）</span></td>
     <td width="210">在线支付</td>
     <td><a href="#" class="book">预 订</a></td>
    </tr></table>
    <div class="tip">
     <div class="tt"></div>
     <div class="con">
      <!-- 后台读取的内容 开始 -->
      <p>1、费用包含：森波拉奇妙世界主题乐园观光门票（不含机动游戏值）+冰川水谷乐园门票 1张；</p>
      <p>2、各园区营业时间：奇妙世界：09：00--18：00（17：00停止换票）；冰川水谷：10：00--23：00；</p>
      <p>3、景区地址：广东省清远市佛冈县羊角山森波拉度假森林内；</p>
      <table style="margin-top:10px"><tr>
       <td width="32" valign="top" style="padding-top:5px"><img src="/static/p/xl.jpg" /></td>
       <td style="color:#e8e8e8">备注：a、此套票奇妙世界门票为观光票不含机动游戏值，如客人需要游玩景区内其他园中园收费项目,费用请游客自理； b、冰川水谷景区内如储物柜、泳圈等需收取相应的租用费用，如客人需使用请自理费用！c、本产品不含旅游人身意外险，我们强烈建议游客购买。游客可在填写订单时勾选附件产品中的相关保险购买；</td>
      </tr></table>
      <!-- 后台读取的内容 结束 -->
     </div>
    </div>
   </dd>
   <dd>
    <table><tr>
     <td width="490">
      <a href="javascript:void(0)" class="name">亚龙湾热带天堂森林公园门票</a>
      <a href="#" class="tip">点评奖金</a>
     </td>
     <td width="396"><b>￥128</b><span>（门店价：235）</span></td>
     <td width="210">在线支付</td>
     <td><a href="#" class="book">预 订</a></td>
    </tr></table>
    <div class="tip">
     <div class="tt"></div>
     <div class="con">
      <!-- 后台读取的内容 开始 -->
      <p>1、费用包含：森波拉奇妙世界主题乐园观光门票（不含机动游戏值）+冰川水谷乐园门票 1张；</p>
      <p>2、各园区营业时间：奇妙世界：09：00--18：00（17：00停止换票）；冰川水谷：10：00--23：00；</p>
      <p>3、景区地址：广东省清远市佛冈县羊角山森波拉度假森林内；</p>
      <table style="margin-top:10px"><tr>
       <td width="32" valign="top" style="padding-top:5px"><img src="/static/p/xl.jpg" /></td>
       <td style="color:#e8e8e8">备注：a、此套票奇妙世界门票为观光票不含机动游戏值，如客人需要游玩景区内其他园中园收费项目,费用请游客自理； b、冰川水谷景区内如储物柜、泳圈等需收取相应的租用费用，如客人需使用请自理费用！c、本产品不含旅游人身意外险，我们强烈建议游客购买。游客可在填写订单时勾选附件产品中的相关保险购买；</td>
      </tr></table>
      <!-- 后台读取的内容 结束 -->
     </div>
    </div>
   </dd>
  </dl>
  <dl>
   <dt>组合套餐</dt>
   <dd>
    <table><tr>
     <td width="490">
      <a href="javascript:void(0)" class="name">亚龙湾热带天堂森林公园门票</a>
     </td>
     <td width="396"><b>￥128</b><span>（门店价：235）</span></td>
     <td width="210">在线支付</td>
     <td><a href="#" class="book">预 订</a></td>
    </tr></table>
    <div class="tip">
     <div class="tt"></div>
     <div class="con">
      <!-- 后台读取的内容 开始 -->
      <p>1、费用包含：森波拉奇妙世界主题乐园观光门票（不含机动游戏值）+冰川水谷乐园门票 1张；</p>
      <p>2、各园区营业时间：奇妙世界：09：00--18：00（17：00停止换票）；冰川水谷：10：00--23：00；</p>
      <p>3、景区地址：广东省清远市佛冈县羊角山森波拉度假森林内；</p>
      <table style="margin-top:10px"><tr>
       <td width="32" valign="top" style="padding-top:5px"><img src="/static/p/xl.jpg" /></td>
       <td style="color:#e8e8e8">备注：a、此套票奇妙世界门票为观光票不含机动游戏值，如客人需要游玩景区内其他园中园收费项目,费用请游客自理； b、冰川水谷景区内如储物柜、泳圈等需收取相应的租用费用，如客人需使用请自理费用！c、本产品不含旅游人身意外险，我们强烈建议游客购买。游客可在填写订单时勾选附件产品中的相关保险购买；</td>
      </tr></table>
      <!-- 后台读取的内容 结束 -->
     </div>
    </div>
   </dd>
  </dl>
 </div>
</div>
<!-- 门票预订 结束 -->

<!-- 门票详情 开始 -->
<div class="mainbox ticket-dest">
 <div class="left">
  <div class="box">
   <a name="notice"></a>
   <h1 class="tit">预订须知</h1>
   <div class="con">
    <!-- 后台读取的内容 开始 -->
    ${scenic.notice!""}
    <!-- 后台读取的内容 结束 -->
   </div>
  </div>
  
  <div class="box">
   <a name="introduce"></a>
   <h1 class="tit">景点介绍</h1>
   <div class="con">
    <!-- 后台读取的内容 开始 -->
     ${scenic.introduce!""}
    <!-- 后台读取的内容 结束 -->
   </div>
  </div>
  
  <div class="box">
   <a name="review"></a>
   <h1 class="tit">用户点评</h1>
   <div class="review">
    <div class="grade">
     <div class="gl">
      <div>综合评分<span>4.7分</span></div>
      <p>(好评率<span><b>88.2%</b></span>，<br/>点评来自<b>500</b>位真实游客)</p>
     </div>
     <dl>
      <dt>游客印象：</dt>
      <dd><a href="#">地方大</a></dd>
      <dd><a href="#">有美食</a></dd>
      <dd><a href="#">比基尼海滩</a></dd>
     </dl>
     <div class="gr">
      <a href="#">发表点评</a>
      <span>（评论返现50积分）</span>
     </div>
    </div>
    
    <ul class="tab">
     <li><a href="#" class="over"><span>全部</span>（278）</a></li>
     <li><a href="#"><span>好评</span>（200）</a></li>
     <li><a href="#"><span>中评</span>（60）</a></li>
     <li><a href="#"><span>差评</span>(18)</a></li>
    </ul>
    
    <ul id="Comment" class="comm">
     <li>
      <div class="user">
       <img src="/static/p/ce2.jpg" />
       <span>thorn</span>
      </div>
      <div class="cr">
       <div class="crj"></div>
       <div class="crt">
        <dl>
         <dt>印象：</dt>
         <dd><a href="#">地方大</a></dd>
         <dd><a href="#">有美食</a></dd>
         <dd><a href="#">比基尼海滩</a></dd>
        </dl>
        <span>2014-09-26 08:38</span>
       </div>
       <p class="crc">网上订票、景点门口取票挺方便的。不过景点地方不是很大，冰川水谷可玩的项目不是很多，而且有几个项目没有开放，景区工作人员不多。奇妙世界是我去过的最差景点之一，没有任何观赏性，而且邮寄农场部分园区充满异味... </p>
       <div class="from">
        <span>来着XXPC网页</span>
        <a href="javascript:void(0)" class="re">回复(0)</a>
        <a href="javascript:void(0)">有用(0)</a>
       </div>
       <form>
        <div class="fj"></div>
        <table><tr>
         <td width="5%">回复thorn:</td>
         <td width="94%"><input type="text" class="msg" /></td>
         <td class="bt"><input type="submit" /></td>
        </tr></table>
       </form>
      </div>
     </li>
     <li>
      <div class="user">
       <img src="/static/p/ce2.jpg" />
       <span>thorn</span>
      </div>
      <div class="cr">
       <div class="crj"></div>
       <div class="crt">
        <dl>
         <dt>印象：</dt>
         <dd><a href="#">地方大</a></dd>
         <dd><a href="#">有美食</a></dd>
         <dd><a href="#">比基尼海滩</a></dd>
        </dl>
        <span>2014-09-26 08:38</span>
       </div>
       <p class="crc">网上订票、景点门口取票挺方便的。不过景点地方不是很大，冰川水谷可玩的项目不是很多，而且有几个项目没有开放，景区工作人员不多。奇妙世界是我去过的最差景点之一，没有任何观赏性，而且邮寄农场部分园区充满异味... </p>
       <div class="from">
        <span>来着XXPC网页</span>
        <a href="javascript:void(0)" class="re">回复(0)</a>
        <a href="javascript:void(0)">有用(0)</a>
       </div>
       <form>
        <div class="fj"></div>
        <table><tr>
         <td width="5%">回复thorn:</td>
         <td width="94%"><input type="text" class="msg" /></td>
         <td class="bt"><input type="submit" /></td>
        </tr></table>
       </form>
      </div>
     </li>
     <li>
      <div class="user">
       <img src="/static/p/ce2.jpg" />
       <span>thorn</span>
      </div>
      <div class="cr">
       <div class="crj"></div>
       <div class="crt">
        <dl>
         <dt>印象：</dt>
         <dd><a href="#">地方大</a></dd>
         <dd><a href="#">有美食</a></dd>
         <dd><a href="#">比基尼海滩</a></dd>
        </dl>
        <span>2014-09-26 08:38</span>
       </div>
       <p class="crc">网上订票、景点门口取票挺方便的。不过景点地方不是很大，冰川水谷可玩的项目不是很多，而且有几个项目没有开放，景区工作人员不多。奇妙世界是我去过的最差景点之一，没有任何观赏性，而且邮寄农场部分园区充满异味... </p>
       <div class="from">
        <span>来着XXPC网页</span>
        <a href="javascript:void(0)" class="re">回复(0)</a>
        <a href="javascript:void(0)">有用(0)</a>
       </div>
       <form>
        <div class="fj"></div>
        <table><tr>
         <td width="5%">回复thorn:</td>
         <td width="94%"><input type="text" class="msg" /></td>
         <td class="bt"><input type="submit" /></td>
        </tr></table>
       </form>
      </div>
     </li>
    </ul>
    
    <div class="page">
     <div class="tag">
      <div class="prev"><a href="#">&lt;</a></div>
      <ul>
       <li><a href="#" class="over">1</a></li>
       <li><a href="#">2</a></li>
       <li><a href="#">3</a></li>
       <li><a href="#">4</a></li>
      </ul>
      <div class="next"><a href="#">&gt;</a></div>
     </div>
    </div>
   </div>
  </div>
 </div>
 
 <div class="right">
  <div class="rela">
   <h4>相关酒店</h4>
   <dl>
    <dt><a href="#"><img src="/static/p/hr14.jpg" /></a></dt>
    <dd>
     <div class="ellipsis"><a href="#">渔民新村大酒店</a></div>
     <span><b>¥380</b>起</span>
     <p>距离约7.847km</p>
    </dd>
    <dd>
     <div class="ellipsis"><a href="#">渔民新村大酒店</a></div>
     <span><b>¥380</b>起</span>
     <p>距离约7.847km</p>
    </dd>
   </dl>
  </div>
  <a href="#" class="pub"><img src="/static/p/pub1.jpg" /></a>
  <a href="#" class="pub"><img src="/static/p/pub2.jpg" /></a>
  <a href="#" class="pub"><img src="/static/p/pub3.jpg" /></a>
 </div>
</div>
<!-- 门票详情 结束 -->

<!-- 通用页脚 开始 -->
<#include "/commons/_inc_foot.ftl" />
<!-- 通用页脚 结束 -->
</body>
</html>
