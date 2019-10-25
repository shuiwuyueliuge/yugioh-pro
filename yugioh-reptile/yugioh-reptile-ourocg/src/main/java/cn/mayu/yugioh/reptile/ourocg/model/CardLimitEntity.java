package cn.mayu.yugioh.reptile.ourocg.model;

import org.springframework.data.mongodb.core.mapping.Document;
import cn.mayu.yugioh.api.mongo.dto.CardLimitMongoDTO;

@Document(collection = "limit")
public class CardLimitEntity extends CardLimitMongoDTO {

}
