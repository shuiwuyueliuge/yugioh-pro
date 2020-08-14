package cn.mayu.yugioh.transform.service;

import java.util.List;
import java.util.stream.Collectors;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;
import cn.mayu.yugioh.transform.manager.CardManagerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.transform.model.entity.AdjustEntity;
import cn.mayu.yugioh.transform.model.entity.EffectEntity;
import cn.mayu.yugioh.transform.model.entity.LinkEntity;
import cn.mayu.yugioh.transform.model.entity.TypeEntity;
import cn.mayu.yugioh.transform.repository.AdjustRepository;
import cn.mayu.yugioh.transform.repository.EffectRepository;
import cn.mayu.yugioh.transform.repository.LinkRepository;
import cn.mayu.yugioh.transform.repository.TypeRepository;

@Service
public class CardInfoServiceImpl implements CardInfoService {

	@Autowired
	private AdjustRepository adjustRepository;

	@Autowired
	private EffectRepository effectRepository;

	@Autowired
	private LinkRepository linkRepository;

	@Autowired
	private IndexService indexService;

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private CardManagerContext cardManagerContext;

	@Override
	@Transactional
	public void saveAdjust(String adjust, Integer cardId, Integer cardType) {
		if (adjust == null || adjust.equals(""))
			return;
		AdjustEntity adjustSaved = adjustRepository.findByCardIdAndTypeVal(cardId, cardType);
		if (adjustSaved == null)
			adjustSaved = new AdjustEntity();
		adjustSaved.setCardId(cardId);
		adjustSaved.setAdjust(adjust);
		adjustSaved.setTypeVal(cardType);
		adjustRepository.save(adjustSaved);
	}

	@Override
	@Transactional
	public void saveEffect(CardProto.CardDetail card, Integer cardId, Integer cardType) {
		EffectEntity effectSaved = effectRepository.findByCardIdAndTypeVal(cardId, cardType);
		if (effectSaved == null) effectSaved = new EffectEntity();
		effectSaved.setCardId(cardId);
		effectSaved.setEffect(card.getDesc());
		effectSaved.setEffectNw(card.getDescNw());
		effectSaved.setEffectJa(card.getDescJa());
		effectSaved.setEffectEn(card.getDescEn());
		effectSaved.setTypeVal(cardType);
		effectRepository.save(effectSaved);
	}

	@Override
	@Transactional
	public void saveLinks(List<String> linkArrow, Integer cardId) {
		if (linkArrow == null || linkArrow.size() <= 0) return;
		List<LinkEntity> links = linkRepository.findByCardId(cardId);
		if (links == null || links.size() <= 0) {
			links = linkArrow.stream().map(link -> {
				LinkEntity linkEntity = new LinkEntity();
				linkEntity.setCardId(cardId);
				linkEntity.setLinkArrow(indexService.findByNameFromCache(1, link));
				return linkEntity;
			}).collect(Collectors.toList());
			linkRepository.saveAll(links);
		}
	}

	@Override
	@Transactional
	public void saveTypes(List<Integer> types, Integer cardId) {
		List<TypeEntity> typeEntities = typeRepository.findByCardId(cardId);
		if (typeEntities == null || typeEntities.size() <= 0) {
            typeEntities = types.stream().map(type -> {
                TypeEntity typeEntity = new TypeEntity();
                typeEntity.setCardId(cardId);
                typeEntity.setTypeSt(type);
                return typeEntity;
            }).collect(Collectors.toList());
			typeRepository.saveAll(typeEntities);
		}
	}

	@Override
	public List<CardDetail> findByIdAndTypeVal(List<CardDetail> details) {
		return cardManagerContext.findByIdAndTypeVal(details);
	}
}
