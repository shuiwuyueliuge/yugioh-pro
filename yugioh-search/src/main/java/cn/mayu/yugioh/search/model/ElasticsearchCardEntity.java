package cn.mayu.yugioh.search.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import java.util.List;

@Data
@Document(indexName = "card")
public class ElasticsearchCardEntity {

    private String name;

    private String effect;

    private Integer typeVal;

    private Integer link;

    private Integer def;

    private Integer pend;

    private Integer race;

    private Integer type;

    private Integer id;

    private Integer attribute;

    private Integer level;

    private Integer atk;

    private String typeSt; // example {1 2 3}

    private String linkArrow; // example {1 2 3}
}
