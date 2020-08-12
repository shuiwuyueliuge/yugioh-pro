package cn.mayu.yugioh.common.facade.transform;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import java.util.ArrayList;
import java.util.List;

public class CardFacadeFallBack implements CardFacade {

    @Override
    public List<CardDetail> findByIdAndTypeVal(List<CardDetail> details) {
        return new ArrayList<>();
    }
}
