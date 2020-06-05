package cn.mayu.yugioh.search.service;

import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import cn.mayu.yugioh.common.dto.search.CardSpecificationDTO;
import cn.mayu.yugioh.search.repository.ElasticSearchRepository;
import cn.mayu.yugioh.search.repository.model.ElasticsearchCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private ElasticSearchRepository elasticSearchRepository;

    @Override
    public List<CardDetail> searchCard(CardSpecificationDTO cardSpecification) {
        List<ElasticsearchCardEntity> cards = elasticSearchRepository.searchCard(cardSpecification);
        return null;
    }
}
