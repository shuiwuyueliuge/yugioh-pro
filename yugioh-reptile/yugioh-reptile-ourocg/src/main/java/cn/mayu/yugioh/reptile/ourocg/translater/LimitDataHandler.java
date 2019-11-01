package cn.mayu.yugioh.reptile.ourocg.translater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;

@Component
public class LimitDataHandler extends DefaultHtmlHandler<List<LimitEntity>> {

	@Override
	protected List<LimitEntity> htmlTranslate(HtmlParser parser) throws Exception {
		String[] res = parser.parseByClassIndex("limit-list sidebar-list", 0).parseByTag("li");
		List<String> limitUrls = Stream.of(res).map(tagOfA -> parseLimitUrl(parser, tagOfA)).collect(Collectors.toList());
		
		String html = parser.connUrl(limitUrls.get(0)).toString();
		String tatile = parser.setHtml(html).parseByClassIndex("title", 0).toString();
		String[] tbodys = parser.setHtml(html).parseByTag("tbody");
		
		
//		for (String url : limitUrls) {
//			
//		}
		
		List<LimitEntity> list = new ArrayList<LimitEntity>();
//		for (String string : urls) {
//			Map<String, List<String>> map = getLimitHashId(string);
//			LimitEntity dto = new LimitEntity();
//			dto.setName(map.get("name").get(0));
//			dto.setForbidden(map.get("forbidden"));
//			dto.setLimited(map.get("limited"));
//			dto.setSemiLimited(map.get("semiLimited"));
//			list.add(dto);
//		}

		return list;
	}
	
	private String parseLimitUrl(HtmlParser parser, String html) {
		return parser.setHtml(html).parseByTagIndexArr("a", 0, "href").toString();
	}
	
	private Map<String, List<String>> getLimitHashId(String url) throws Exception {
		Document doc = Jsoup.connect(url).get();
		String name = doc.getElementsByClass("title").html();
		List<String> names = new ArrayList<String>(1);
		names.add(name);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("name", names);
		List<String> forbiddens = doc.getElementsByTag("tbody").get(0).getElementsByTag("tr").stream().map(el -> {
			String str = el.getElementsByTag("a").get(0).attr("href");
			str = str.substring(str.lastIndexOf("/") + 1, str.length());
			return str;
		}).collect(Collectors.toList());
		map.put("forbidden", forbiddens);
		List<String> limiteds = doc.getElementsByTag("tbody").get(1).getElementsByTag("tr").stream().map(el -> {
			String str = el.getElementsByTag("a").get(0).attr("href");
			str = str.substring(str.lastIndexOf("/") + 1, str.length());
			return str;
		}).collect(Collectors.toList());
		map.put("limited", limiteds);
		List<String> semiLimiteds = doc.getElementsByTag("tbody").get(2).getElementsByTag("tr").stream().map(el -> {
			String str = el.getElementsByTag("a").get(0).attr("href");
			str = str.substring(str.lastIndexOf("/") + 1, str.length());
			return str;
		}).collect(Collectors.toList());
		map.put("semiLimited", semiLimiteds);
		return map;
	}
}


/**
 * [<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #317cbb"></div><a href="https://www.ourocg.cn/card/nJsmED" target="_blank">幻崩哥布林</a></td> 
 <td class="hidden-xs">2</td> 
 <td class="hidden-xs">风</td> 
 <td class="hidden-xs">恶魔</td> 
 <td class="hidden-xs">1300</td> 
 <td class="hidden-xs"></td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #317cbb"></div><a href="https://www.ourocg.cn/card/65sKeJ" target="_blank">幻崩人鱼</a></td> 
 <td class="hidden-xs">1</td> 
 <td class="hidden-xs">水</td> 
 <td class="hidden-xs">恶魔</td> 
 <td class="hidden-xs">1000</td> 
 <td class="hidden-xs"></td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #317cbb"></div><a href="https://www.ourocg.cn/card/16sRe9" target="_blank">召唤女术士</a></td> 
 <td class="hidden-xs">3</td> 
 <td class="hidden-xs">暗</td> 
 <td class="hidden-xs">魔法师</td> 
 <td class="hidden-xs">2400</td> 
 <td class="hidden-xs"></td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #317cbb"></div><a href="https://www.ourocg.cn/card/9vszOW" target="_blank">防火墙龙</a></td> 
 <td class="hidden-xs">0</td> 
 <td class="hidden-xs">光</td> 
 <td class="hidden-xs">电子界</td> 
 <td class="hidden-xs">2500</td> 
 <td class="hidden-xs"></td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/awsoKz" target="_blank">真龙剑皇 统领星杰P</a></td> 
 <td class="hidden-xs">8</td> 
 <td class="hidden-xs">光</td> 
 <td class="hidden-xs">幻龙</td> 
 <td class="hidden-xs">2950</td> 
 <td class="hidden-xs">2950</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #222542"></div><a href="https://www.ourocg.cn/card/LAsZlN" target="_blank">十二兽 枪戟龙</a></td> 
 <td class="hidden-xs">4</td> 
 <td class="hidden-xs">地</td> 
 <td class="hidden-xs">兽战士</td> 
 <td class="hidden-xs">?</td> 
 <td class="hidden-xs">?</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #222542"></div><a href="https://www.ourocg.cn/card/lNsg6N" target="_blank">十二兽 犄角牛</a></td> 
 <td class="hidden-xs">4</td> 
 <td class="hidden-xs">地</td> 
 <td class="hidden-xs">兽战士</td> 
 <td class="hidden-xs">?</td> 
 <td class="hidden-xs">?</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/Qbsxr6" target="_blank">BF-胧影之高弗</a></td> 
 <td class="hidden-xs">5</td> 
 <td class="hidden-xs">暗</td> 
 <td class="hidden-xs">鸟兽</td> 
 <td class="hidden-xs">0</td> 
 <td class="hidden-xs">0</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/77sAoA" target="_blank">破灭龙 刚多拉X</a></td> 
 <td class="hidden-xs">8</td> 
 <td class="hidden-xs">暗</td> 
 <td class="hidden-xs">龙</td> 
 <td class="hidden-xs">0</td> 
 <td class="hidden-xs">0</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/o0sQ7w" target="_blank">娱乐伙伴 琴键猴</a></td> 
 <td class="hidden-xs">6</td> 
 <td class="hidden-xs">地</td> 
 <td class="hidden-xs">兽</td> 
 <td class="hidden-xs">1000</td> 
 <td class="hidden-xs">2400</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/MKs6Xp" target="_blank">威风妖怪 麒麟</a></td> 
 <td class="hidden-xs">6</td> 
 <td class="hidden-xs">风</td> 
 <td class="hidden-xs">魔法师</td> 
 <td class="hidden-xs">2000</td> 
 <td class="hidden-xs">2000</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/LAsDqA" target="_blank">娱乐法师 火布偶</a></td> 
 <td class="hidden-xs">4</td> 
 <td class="hidden-xs">炎</td> 
 <td class="hidden-xs">魔法师</td> 
 <td class="hidden-xs">1000</td> 
 <td class="hidden-xs">1000</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #222542"></div><a href="https://www.ourocg.cn/card/lNsr1q" target="_blank">星守骑士 托勒密</a></td> 
 <td class="hidden-xs">4</td> 
 <td class="hidden-xs">光</td> 
 <td class="hidden-xs">战士</td> 
 <td class="hidden-xs">550</td> 
 <td class="hidden-xs">2600</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #222542"></div><a href="https://www.ourocg.cn/card/wvsRna" target="_blank">No.95 银河眼暗物质龙</a></td> 
 <td class="hidden-xs">9</td> 
 <td class="hidden-xs">暗</td> 
 <td class="hidden-xs">龙</td> 
 <td class="hidden-xs">4000</td> 
 <td class="hidden-xs">0</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #865395"></div><a href="https://www.ourocg.cn/card/3zs691" target="_blank">旧神 诺登</a></td> 
 <td class="hidden-xs">4</td> 
 <td class="hidden-xs">水</td> 
 <td class="hidden-xs">天使</td> 
 <td class="hidden-xs">2000</td> 
 <td class="hidden-xs">2200</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/27smPg" target="_blank">BF-隐蓑之斯奇姆</a></td> 
 <td class="hidden-xs">3</td> 
 <td class="hidden-xs">暗</td> 
 <td class="hidden-xs">鸟兽</td> 
 <td class="hidden-xs">800</td> 
 <td class="hidden-xs">1200</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/vasMpX" target="_blank">焰征龙-爆炎</a></td> 
 <td class="hidden-xs">7</td> 
 <td class="hidden-xs">炎</td> 
 <td class="hidden-xs">龙</td> 
 <td class="hidden-xs">2800</td> 
 <td class="hidden-xs">1800</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/PPse76" target="_blank">瀑征龙-潮水</a></td> 
 <td class="hidden-xs">7</td> 
 <td class="hidden-xs">水</td> 
 <td class="hidden-xs">龙</td> 
 <td class="hidden-xs">2600</td> 
 <td class="hidden-xs">2000</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/NRs2xM" target="_blank">岩征龙-原地</a></td> 
 <td class="hidden-xs">7</td> 
 <td class="hidden-xs">地</td> 
 <td class="hidden-xs">龙</td> 
 <td class="hidden-xs">1600</td> 
 <td class="hidden-xs">3000</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #222542"></div><a href="https://www.ourocg.cn/card/nJsbvJ" target="_blank">M.X-剑客 招来者</a></td> 
 <td class="hidden-xs">3</td> 
 <td class="hidden-xs">地</td> 
 <td class="hidden-xs">战士</td> 
 <td class="hidden-xs">1600</td> 
 <td class="hidden-xs">500</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #222542"></div><a href="https://www.ourocg.cn/card/57sKp0" target="_blank">灼热熔岩带的锁链龙</a></td> 
 <td class="hidden-xs">4</td> 
 <td class="hidden-xs">炎</td> 
 <td class="hidden-xs">海龙</td> 
 <td class="hidden-xs">1800</td> 
 <td class="hidden-xs">1000</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #222542"></div><a href="https://www.ourocg.cn/card/LAsXnp" target="_blank">No.16 色之支配者</a></td> 
 <td class="hidden-xs">4</td> 
 <td class="hidden-xs">光</td> 
 <td class="hidden-xs">天使</td> 
 <td class="hidden-xs">2300</td> 
 <td class="hidden-xs">1600</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/43s5R9" target="_blank">发条猎手</a></td> 
 <td class="hidden-xs">3</td> 
 <td class="hidden-xs">暗</td> 
 <td class="hidden-xs">兽战士</td> 
 <td class="hidden-xs">1600</td> 
 <td class="hidden-xs">500</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/wvsbE7" target="_blank">成长球茎</a></td> 
 <td class="hidden-xs">1</td> 
 <td class="hidden-xs">地</td> 
 <td class="hidden-xs">植物</td> 
 <td class="hidden-xs">100</td> 
 <td class="hidden-xs">100</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/ZXs7Dg" target="_blank">暴君海王星</a></td> 
 <td class="hidden-xs">10</td> 
 <td class="hidden-xs">水</td> 
 <td class="hidden-xs">爬虫类</td> 
 <td class="hidden-xs">0</td> 
 <td class="hidden-xs">0</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/g5sB1J" target="_blank">等级剽虫</a></td> 
 <td class="hidden-xs">1</td> 
 <td class="hidden-xs">暗</td> 
 <td class="hidden-xs">昆虫</td> 
 <td class="hidden-xs">600</td> 
 <td class="hidden-xs">0</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #e2ddda"></div><a href="https://www.ourocg.cn/card/0zsRlE" target="_blank">远古妖精龙</a></td> 
 <td class="hidden-xs">7</td> 
 <td class="hidden-xs">光</td> 
 <td class="hidden-xs">龙</td> 
 <td class="hidden-xs">2100</td> 
 <td class="hidden-xs">3000</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/DbsKPK" target="_blank">鱼类电动人-枪手</a></td> 
 <td class="hidden-xs">1</td> 
 <td class="hidden-xs">水</td> 
 <td class="hidden-xs">鱼</td> 
 <td class="hidden-xs">100</td> 
 <td class="hidden-xs">200</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/DbsKax" target="_blank">凤形曼珠沙华</a></td> 
 <td class="hidden-xs">8</td> 
 <td class="hidden-xs">炎</td> 
 <td class="hidden-xs">植物</td> 
 <td class="hidden-xs">2200</td> 
 <td class="hidden-xs">0</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"></div><a href="https://www.ourocg.cn/card/qPseVQ" target="_blank">精神大师</a></td> 
 <td class="hidden-xs">1</td> 
 <td class="hidden-xs">光</td> 
 <td class="hidden-xs">念动力</td> 
 <td class="hidden-xs">100</td> 
 <td class="hidden-xs">200</td> 
</tr> 
<tr> 
 <td class="cname">
  <div class="typeIcon" style="border-color: #b76d44"...
 * 
 */
