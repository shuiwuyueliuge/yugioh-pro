package cn.mayu.yugioh.sync.local.service;

import static cn.mayu.yugioh.common.core.util.StringUtil.effectFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.local.entity.AdjustEntity;
import cn.mayu.yugioh.sync.local.entity.EffectEntity;
import cn.mayu.yugioh.sync.local.entity.ImgEntity;
import cn.mayu.yugioh.sync.local.repository.AdjustRepository;
import cn.mayu.yugioh.sync.local.repository.EffectRepository;
import cn.mayu.yugioh.sync.local.repository.ImgRepository;

@Service
public class OtherInfoServiceImpl implements OtherInfoService {

	@Autowired
	private AdjustRepository adjustRepository;

	@Autowired
	private EffectRepository effectRepository;

	@Autowired
	private ImgRepository imgRepository;

	@Override
	@Transactional
	public void saveOtherData(CardDataEntity entity) {
		saveAdjust(entity);
		saveEffect(entity);
		saveImg(entity);
	}

	@Transactional
	private void saveAdjust(CardDataEntity entity) {
		if (entity.getAdjust().equals("")) {
			return;
		}

		AdjustEntity adjust = new AdjustEntity();
		adjust.setCardId(entity.getId());
		adjust.setAdjust(entity.getAdjust());
		adjust.setTypeVal(entity.getTypeVal());
		adjustRepository.save(adjust);
	}

	@Transactional
	private void saveEffect(CardDataEntity entity) {
		EffectEntity effect = new EffectEntity();
		effect.setCardId(entity.getId());
		effect.setEffect(effectFormat(entity.getDesc()));
		effect.setEffectNw(effectFormat(entity.getDescNw()));
		effect.setTypeVal(entity.getTypeVal());
		effectRepository.save(effect);
	}

	@Transactional
	private void saveImg(CardDataEntity entity) {
		if (entity.getImgUrl() == null) {
			return;
		}

		ImgEntity img = new ImgEntity();
		img.setCardId(entity.getId());
		img.setImg(entity.getImgUrl());
		img.setTypeVal(entity.getTypeVal());
		imgRepository.save(img);
	}
}
