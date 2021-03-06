package cn.mayu.yugioh.transform.manager;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;
import cn.mayu.yugioh.transform.model.MagicTrapCardDetailConverterFactory;
import cn.mayu.yugioh.transform.model.entity.AdjustEntity;
import cn.mayu.yugioh.transform.model.entity.ForbiddenEntity;
import cn.mayu.yugioh.transform.model.entity.PackageInfoEntity;
import cn.mayu.yugioh.transform.repository.*;
import cn.mayu.yugioh.transform.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.transform.model.dto.CardDTO;
import cn.mayu.yugioh.transform.model.dto.CardTypeDTO;
import cn.mayu.yugioh.transform.model.entity.MagicTrapEntity;
import cn.mayu.yugioh.transform.service.CardInfoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MagicTrapCardManager implements CardManager {
	
	@Autowired
	private MagicTrapRepository magicTrapRepository;
	
	@Autowired
	private CardInfoService cardInfoService;

	@Autowired
	private AdjustRepository adjustRepository;

	@Autowired
	private ForbiddenRepository forbiddenRepository;

	@Autowired
	private PackageInfoRepository packageInfoRepository;

	@Autowired
	private RareRepository rareRepository;

	@Autowired
	private IndexService indexService;

	private DomainConverterFactory<MagicTrapEntity, CardDetail> magicTrapEntityCardDetailDomainConverter;

	{
		this.magicTrapEntityCardDetailDomainConverter = new MagicTrapCardDetailConverterFactory();
	}

	@Override
	@Transactional
	public Integer cardSave(CardDTO cardDto) {
		CardTypeDTO cardTypeDto = cardDto.getCardTypeDTO();
		CardProto.CardDetail card = cardDto.getCardDetail();
		MagicTrapEntity magicTrap = magicTrapRepository.findByNameAndPassword(card.getName(), card.getPassword());
		if (magicTrap == null) magicTrap = new MagicTrapEntity();
		magicTrap.setPassword(card.getPassword());
		magicTrap.setName(card.getName());
		magicTrap.setNameJa(card.getNameJa());
		magicTrap.setNameEn(card.getNameEn());
		magicTrap.setNameNw(card.getNameNw());
		magicTrap.setTypeSt(cardTypeDto.getMagicTrapType());
		magicTrap.setTypeVal(cardTypeDto.getCardType());
		magicTrap = magicTrapRepository.save(magicTrap);
		cardInfoService.saveAdjust(card.getAdjust(), magicTrap.getId(), cardTypeDto.getCardType());
		cardInfoService.saveEffect(card, magicTrap.getId(), cardTypeDto.getCardType());
		return magicTrap.getId();
	}

	@Override
	public CardDetail findByIdAndTypeVal(CardDetail detail) { // adjust,card,forbidden,rare
		Optional<MagicTrapEntity> entityOptional = magicTrapRepository.findById(detail.getId());
		MagicTrapEntity entity = entityOptional.get();
		AdjustEntity adjustEntity = adjustRepository.findByCardIdAndTypeVal(detail.getId(), entity.getTypeVal());
		CardDetail cardDetail = magicTrapEntityCardDetailDomainConverter.convert(entity);
		List<PackageInfoEntity> packageInfoEntities = packageInfoRepository.findByCardIdAndTypeVal(entity.getId(), entity.getTypeVal());
		if (packageInfoEntities != null && packageInfoEntities.size() > 0) {
			List<String> rare = packageInfoEntities.stream().map(PackageInfoEntity::getRaceId).collect(Collectors.toSet()).stream().map(data ->
					rareRepository.findById(data).get().getShortName()
			).collect(Collectors.toList());
			cardDetail.setRare(rare);
		}

		if (adjustEntity != null) {
			cardDetail.setAdjust(adjustEntity.getAdjust());
		}

		cardDetail.setImgUrl(String.format("%s_%s.jpg", cardDetail.getId(), cardDetail.getTypeVal()));
		cardDetail.setBan("3");
		Page<ForbiddenEntity> forbiddenEntity =  forbiddenRepository.findByCardNameOrderByLimitTimeDesc(cardDetail.getName(), PageRequest.of(0, 1));
		if (forbiddenEntity.hasContent()) {
			cardDetail.setBan(forbiddenEntity.getContent().get(0).getLimitVal().toString());
		}

		List<String> sts = detail.getTypeSt().stream().map(st -> indexService.findByTypeIndexFromCache( 5, Integer.valueOf(st))).collect(Collectors.toList());
		cardDetail.setTypeSt(sts);
		cardDetail.setTypeVal(indexService.findByTypeIndexFromCache(6, entity.getTypeVal()));
		return cardDetail;
	}
}
