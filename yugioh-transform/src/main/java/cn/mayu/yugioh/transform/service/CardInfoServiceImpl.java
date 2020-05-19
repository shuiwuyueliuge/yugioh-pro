package cn.mayu.yugioh.transform.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import cn.mayu.yugioh.transform.domain.entity.AdjustEntity;
import cn.mayu.yugioh.transform.domain.entity.EffectEntity;
import cn.mayu.yugioh.transform.domain.entity.LinkEntity;
import cn.mayu.yugioh.transform.domain.entity.TypeEntity;
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

	@Override
	@Transactional
	public void saveAdjust(String adjust, Integer cardId) {
		if (adjust == null || adjust.equals(""))
			return;
		AdjustEntity adjustSaved = adjustRepository.findByCardIdAndTypeVal(cardId, 1);
		if (adjustSaved == null)
			adjustSaved = new AdjustEntity();
		adjustSaved.setCardId(cardId);
		adjustSaved.setAdjust(adjust);
		adjustSaved.setTypeVal(1);
		adjustRepository.save(adjustSaved);
	}

	@Override
	@Transactional
	public void saveEffect(CardDetail card, Integer cardId) {
		EffectEntity effectSaved = effectRepository.findByCardIdAndTypeVal(cardId, 1);
		if (effectSaved == null) effectSaved = new EffectEntity();
		effectSaved.setCardId(cardId);
		effectSaved.setEffect(card.getDesc());
		effectSaved.setEffectNw(card.getDescNw());
		effectSaved.setEffectJa(card.getDescJa());
		effectSaved.setEffectEn(card.getDescEn());
		effectSaved.setTypeVal(1);
		effectRepository.save(effectSaved);
	}

	@Override
	@Transactional
	public void saveLinks(List<String> linkArrow, Integer cardId) {
		if (linkArrow == null || linkArrow.size() <= 0) return;
		List<LinkEntity> links = linkRepository.findByCardId(cardId);
		if (links == null || links.size() <= 0) {
			links = new ArrayList<LinkEntity>();
			for (String link : linkArrow) {
				LinkEntity linkEntity = new LinkEntity();
				linkEntity.setCardId(cardId);
				linkEntity.setLinkArrow(indexService.findByNameFromCache(1, link));
				links.add(linkEntity);
			}
			
			linkRepository.saveAll(links);
		} 
	}

	@Override
	@Transactional
	public void saveTypes(List<Integer> types, Integer cardId) {
		List<TypeEntity> typeEntities = typeRepository.findByCardId(cardId);
		if (typeEntities == null || typeEntities.size() <= 0) {
			typeEntities = new ArrayList<TypeEntity>();
			for (Integer type : types) {
				TypeEntity typeEntity = new TypeEntity();
				typeEntity.setCardId(cardId);
				typeEntity.setTypeSt(type);
				typeEntities.add(typeEntity);
			}
			
			typeRepository.saveAll(typeEntities);
		}
	}
}
