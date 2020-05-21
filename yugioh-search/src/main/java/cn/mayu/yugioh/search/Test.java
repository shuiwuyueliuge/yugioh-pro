package cn.mayu.yugioh.search;

import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface Test extends ElasticsearchRepository<Card, String> {

	@Query("{\"query\":{\"match_phrase\":{\"name\":\"蓝眼\"}}}")
	List<Card> findByName();
}
