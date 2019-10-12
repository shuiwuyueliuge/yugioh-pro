package cn.mayu.yugioh.reptile.ourocg.model;

import org.springframework.data.mongodb.core.mapping.Document;
import cn.mayu.yugioh.api.mongo.dto.CardDataMongoDTO;

@Document(collection = "arr")
public class CardInfoEntity extends CardDataMongoDTO {

}
