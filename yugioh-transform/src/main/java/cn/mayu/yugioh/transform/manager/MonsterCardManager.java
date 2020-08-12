package cn.mayu.yugioh.transform.manager;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;
import cn.mayu.yugioh.transform.model.MonsterEntityCardDetailConverterFactory;
import cn.mayu.yugioh.transform.model.entity.AdjustEntity;
import cn.mayu.yugioh.transform.model.entity.ForbiddenEntity;
import cn.mayu.yugioh.transform.model.entity.PackageInfoEntity;
import cn.mayu.yugioh.transform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.transform.model.MonsterConverterFactory;
import cn.mayu.yugioh.transform.model.dto.CardDTO;
import cn.mayu.yugioh.transform.model.dto.CardTypeDTO;
import cn.mayu.yugioh.transform.model.entity.MonsterEntity;
import cn.mayu.yugioh.transform.service.CardInfoService;
import cn.mayu.yugioh.transform.service.IndexService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MonsterCardManager implements CardManager {

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private CardInfoService cardInfoService;

    private DomainConverterFactory<CardProto.CardDetail, MonsterEntity> monsterConverterFactory;

    private DomainConverterFactory<MonsterEntity, CardDetail> monsterEntityCardDetailDomainConverter;

    @Autowired
    private AdjustRepository adjustRepository;

    @Autowired
    private ForbiddenRepository forbiddenRepository;

    @Autowired
    private IndexService indexService;

    @Autowired
    private PackageInfoRepository packageInfoRepository;

    @Autowired
    private RareRepository rareRepository;

    @Autowired
    public MonsterCardManager(IndexService indexService) {
        monsterConverterFactory = new MonsterConverterFactory(indexService);
        monsterEntityCardDetailDomainConverter = new MonsterEntityCardDetailConverterFactory();
    }

    @Override
    @Transactional
    public Integer cardSave(CardDTO cardDto) {
        CardTypeDTO cardTypeDto = cardDto.getCardTypeDTO();
        CardProto.CardDetail card = cardDto.getCardDetail();
        MonsterEntity monster = monsterConverterFactory.convert(card);
        MonsterEntity monsterSaved = monsterRepository.findByNameAndPassword(card.getName(), card.getPassword());
        if (monsterSaved != null) monster.setId(monsterSaved.getId());
        monsterSaved = monsterRepository.save(monster);
        cardInfoService.saveAdjust(card.getAdjust(), monsterSaved.getId(), cardTypeDto.getCardType());
        cardInfoService.saveEffect(card, monsterSaved.getId(), cardTypeDto.getCardType());
        cardInfoService.saveLinks(card.getLinkArrowList(), monsterSaved.getId());
        cardInfoService.saveTypes(cardTypeDto.getMonsterType(), monsterSaved.getId());
        return monsterSaved.getId();
    }

    @Override
    public CardDetail findByIdAndTypeVal(CardDetail detail) { // adjust,card,forbidden,rare
        Optional<MonsterEntity> optionalMonsterEntity = monsterRepository.findById(detail.getId());
        MonsterEntity entity = optionalMonsterEntity.get();
        AdjustEntity adjustEntity = adjustRepository.findByCardIdAndTypeVal(detail.getId(), 1);
        CardDetail cardDetail = monsterEntityCardDetailDomainConverter.convert(entity);
        List<PackageInfoEntity> packageInfoEntities = packageInfoRepository.findByCardIdAndTypeVal(entity.getId(), 1);
        if (packageInfoEntities != null && packageInfoEntities.size() > 0) {
            List<String> rare = packageInfoEntities.stream().map(PackageInfoEntity::getRaceId).collect(Collectors.toSet()).stream().map(data ->
                rareRepository.findById(data).get().getShortName()
            ).collect(Collectors.toList());
            cardDetail.setRare(rare);
        }

        if (adjustEntity != null) {
            cardDetail.setAdjust(adjustEntity.getAdjust());
        }

        cardDetail.setBan("3");
        Page<ForbiddenEntity> forbiddenEntity = forbiddenRepository.findByCardNameOrderByLimitTimeDesc(cardDetail.getName(), PageRequest.of(0, 1));
        if (forbiddenEntity.hasContent()) {
            cardDetail.setBan(forbiddenEntity.getContent().get(0).getLimitVal().toString());
        }

        cardDetail.setImgUrl(String.format("%s_%s.jpg", cardDetail.getId(), 1));
        cardDetail.setBan(indexService.findByTypeIndexFromCache(8, Integer.valueOf(cardDetail.getBan())));
        cardDetail.setRace(indexService.findByTypeIndexFromCache(3, entity.getRace()));
        cardDetail.setAttribute(indexService.findByTypeIndexFromCache(2, entity.getAttribute()));
        cardDetail.setTypeVal(indexService.findByTypeIndexFromCache(6, 1));
        cardDetail.setLink(entity.getLink() < 0 ? "-" : entity.getLink() + "");
        if (entity.getDef() == -2) {
            cardDetail.setDef("-");
        }

        if (entity.getDef() == -1) {
            cardDetail.setDef("?");
        }

        if (entity.getDef() >= 0) {
            cardDetail.setDef(entity.getDef() + "");
        }

        cardDetail.setAtk(entity.getAtk() < 0 ? "?" : entity.getAtk() + "");
        cardDetail.setPendL(entity.getPendL() < 0 ? "-" : entity.getPendL() + "");
        cardDetail.setPendR(entity.getPendR() < 0 ? "-" : entity.getPendR() + "");
        List<String> sts = detail.getTypeSt().stream().map(st -> indexService.findByTypeIndexFromCache( 4, Integer.valueOf(st))).collect(Collectors.toList());cardDetail.setTypeSt(sts);
        cardDetail.setTypeSt(sts);
        List<String> links = detail.getLinkArrow().stream().map(st -> indexService.findByTypeIndexFromCache(1, Integer.valueOf(st))).collect(Collectors.toList());
        cardDetail.setLinkArrow(links);
        return cardDetail;
    }
}
