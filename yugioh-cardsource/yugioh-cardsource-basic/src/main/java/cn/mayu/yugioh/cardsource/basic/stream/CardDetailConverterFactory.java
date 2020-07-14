package cn.mayu.yugioh.cardsource.basic.stream;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;

import java.util.List;
import java.util.stream.Collectors;

public class CardDetailConverterFactory implements DomainConverterFactory<CardDetail, CardProto.CardDetail> {

    @Override
    public CardProto.CardDetail convert(CardDetail source) {
        CardProto.CardDetail.Builder build = CardProto.CardDetail.newBuilder();
        build.setPassword(source.getPassword());
        build.setSerial(source.getSerial());
        build.setName(source.getName());
        build.setNameJa(source.getNameJa());
        build.setNameEn(source.getNameEn());
        build.setNameNw(source.getNameNw());
        build.setImgUrl(source.getImgUrl());
        build.setAttribute(source.getAttribute());
        build.setLevel(source.getLevel());
        build.setRace(source.getRace());
        build.setAtk(source.getAtk());
        build.setDef(source.getDef());
        build.setPendL(source.getPendL());
        build.setPendR(source.getPendR());
        build.setLink(source.getLink());
        build.setBan(source.getBan());
        build.setDesc(source.getDesc());
        build.setDescJa(source.getDescJa());
        build.setDescEn(source.getDescEn());
        build.setDescNw(source.getDescNw());
        build.setAdjust(source.getAdjust());
        build.addAllTypeSt(source.getTypeSt());
        build.addAllLinkArrow(source.getLinkArrow());
        build.addAllRare(source.getRare());
        return build.build();
    }

    @Override
    public List<CardProto.CardDetail> convertList(List<CardDetail> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }
}
