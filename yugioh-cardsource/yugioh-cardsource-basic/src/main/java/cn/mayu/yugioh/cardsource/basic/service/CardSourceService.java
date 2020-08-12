package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import java.util.List;

public interface CardSourceService {

    List<SourceType> getSourceType();
}
