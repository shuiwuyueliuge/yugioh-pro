package cn.mayu.yugioh.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SearchApp.class);
		app.run(args);
	}

//	@Autowired
//	Test t;
//
//	@Autowired
//	ElasticsearchRestTemplate template;
//
//	@Override
//	public void run(String... args) {
//		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
//				                              .withQuery(QueryBuilders.boolQuery()
//													  .should(QueryBuilders.matchPhraseQuery("name", "黑魔导"))
//													  .should(QueryBuilders.matchPhraseQuery("effect", "蓝眼"))
//													  .should(QueryBuilders.matchPhraseQuery("name", "hero")))
//				                              .build();
//		SearchHits<Card> list = template.search(nativeSearchQuery, Card.class);
//		list.stream().forEach(System.out::println);
//	}
}
