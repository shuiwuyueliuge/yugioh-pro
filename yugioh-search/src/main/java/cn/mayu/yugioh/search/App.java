package cn.mayu.yugioh.search;

import java.util.List;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootApplication
public class App implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Autowired
	Test t;
	
	@Autowired
	ElasticsearchRestTemplate template;

	@Override
	public void run(String... args) throws Exception {
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				                              .withQuery(QueryBuilders.matchPhraseQuery("name", "蓝眼"))
				                              .build();
		
		List<Card> list = template.queryForList(nativeSearchQuery, Card.class);
		list.stream().forEach(System.out::println);
	}
}
