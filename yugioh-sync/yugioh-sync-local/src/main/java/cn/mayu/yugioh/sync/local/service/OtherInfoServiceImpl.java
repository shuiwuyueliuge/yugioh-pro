package cn.mayu.yugioh.sync.local.service;

import static cn.mayu.yugioh.common.core.util.StringUtil.effectFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.local.async.AsyncBoondock;
import cn.mayu.yugioh.sync.local.entity.AdjustEntity;
import cn.mayu.yugioh.sync.local.entity.EffectEntity;
import cn.mayu.yugioh.sync.local.repository.AdjustRepository;
import cn.mayu.yugioh.sync.local.repository.EffectRepository;

@Service
public class OtherInfoServiceImpl implements OtherInfoService {

	@Autowired
	private AdjustRepository adjustRepository;

	@Autowired
	private EffectRepository effectRepository;
	
	@Autowired
	private AsyncBoondock boondock;

	@Override
	@Transactional
	public void saveOtherData(CardDataEntity entity) {
		saveAdjust(entity);
		saveEffect(entity);
		boondock.saveInDisk(entity);
	}

	@Transactional
	private void saveAdjust(CardDataEntity entity) {
		if (entity.getAdjust().equals("")) {
			return;
		}

		AdjustEntity adjust = new AdjustEntity();
		adjust.setCardId(Integer.valueOf(entity.getId()));
		adjust.setAdjust(entity.getAdjust());
		adjust.setTypeVal(entity.getTypeVal());
		adjustRepository.save(adjust);
	}

	@Transactional
	private void saveEffect(CardDataEntity entity) {
		EffectEntity effect = new EffectEntity();
		effect.setCardId(Integer.valueOf(entity.getId()));
		effect.setEffect(effectFormat(entity.getDesc()));
		effect.setEffectNw(effectFormat(entity.getDescNw()));
		effect.setTypeVal(entity.getTypeVal());
		effectRepository.save(effect);
	}
}
