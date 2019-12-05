package cn.mayu.yugioh.sync.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.facade.sync.home.LimitDetilProto.LimitDetilEntity;
import cn.mayu.yugioh.sync.local.entity.ForbiddenEntity;
import cn.mayu.yugioh.sync.local.repository.ForbiddenRepository;

@Service
public class ForbiddenServiceImpl implements ForbiddenService {
	
	@Autowired
	private ForbiddenRepository forbiddenRepository;
	
	@Autowired
	private MonsterService monsterService;

	@Autowired
	private MagicTrapService magicTrapService;

	@Override
	public void saveLimitCard(LimitDetilEntity entity) {
		Integer id = null;
		if (entity.getTypeVal() == 1) {
			id = monsterService.findByNameAndPassword(entity.getCardName(), entity.getPsw()).getId();
		} else {
			id = magicTrapService.findByNameAndPassword(entity.getCardName(), entity.getPsw()).getId();
		}
		
		ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
		forbiddenEntity.setCardId(id);
		forbiddenEntity.setLimitVal(entity.getLimitVal());
		forbiddenEntity.setTypeVal(entity.getTypeVal());
		forbiddenEntity.setLimitTime(entity.getName());
		forbiddenRepository.save(forbiddenEntity);
	}
}
