package cn.mayu.yugioh.search;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "ygo")
public class Card {

	private String link;
    private String level;
    private String name_en;
    private String name_nw;
    private String pend_l;
    private String name_ja;
    private String type;
    private String create_time;
    private String id;
    private String hash_id;
    private String locale;
    private String race;
    private String def;
    private String name;
    private String pend_r;
    private String password;
    private String atk;
    private String attribute;
}
