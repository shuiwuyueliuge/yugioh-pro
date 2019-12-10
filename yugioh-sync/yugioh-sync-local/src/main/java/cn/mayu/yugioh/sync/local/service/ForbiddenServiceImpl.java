package cn.mayu.yugioh.sync.local.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		String[] arr = entity.getName().replace("【", " ").replace("】 ", " ").split(" ");
		LocalDate date = LocalDate.parse(arr[0], DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		forbiddenEntity.setLimitTime(date);
		forbiddenEntity.setType(arr[1].equals("TCG") ? 1 : 0);
		forbiddenRepository.save(forbiddenEntity);
	}
}
