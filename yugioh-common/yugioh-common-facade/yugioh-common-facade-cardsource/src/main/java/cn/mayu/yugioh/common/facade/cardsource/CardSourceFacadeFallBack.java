package cn.mayu.yugioh.common.facade.cardsource;

import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import java.util.ArrayList;
import java.util.List;

public class CardSourceFacadeFallBack implements CardSourceFacade {

    @Override
    public List<SourceType> getSourceType() {
        return new ArrayList<>();
    }
}
