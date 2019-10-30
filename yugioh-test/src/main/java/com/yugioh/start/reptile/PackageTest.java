package com.yugioh.start.reptile;

import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.common.core.html.HtmlVisitor;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.reptile.ourocg.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PackageTest {
	
	@Autowired
	private HtmlVisitor<List<IncludeInfo>> includeInfoTranslater;
	
	@Test
	public void packageDataTest() throws Exception {
		List<IncludeInfo> infos = includeInfoTranslater.visit("https://www.ourocg.cn/card/x9cPxBVZ");
	    assertNull(infos);
	}
}
