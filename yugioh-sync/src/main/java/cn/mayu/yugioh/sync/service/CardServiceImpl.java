package cn.mayu.yugioh.sync.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static cn.mayu.yugioh.common.core.util.StringUtil.*;

import cn.mayu.yugioh.common.core.util.StringUtil;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.sync.entity.AdjustEntity;
import cn.mayu.yugioh.sync.entity.EffectEntity;
import cn.mayu.yugioh.sync.entity.ImgEntity;
import cn.mayu.yugioh.sync.entity.LinkEntity;
import cn.mayu.yugioh.sync.entity.MagicTrapEntity;
import cn.mayu.yugioh.sync.entity.MonsterEntity;
import cn.mayu.yugioh.sync.entity.PackageEntity;
import cn.mayu.yugioh.sync.entity.PackageInfoEntity;
import cn.mayu.yugioh.sync.entity.RareEntity;
import cn.mayu.yugioh.sync.entity.TypeEntity;
import cn.mayu.yugioh.sync.repository.AdjustRepository;
import cn.mayu.yugioh.sync.repository.CardRepository;
import cn.mayu.yugioh.sync.repository.EffectRepository;
import cn.mayu.yugioh.sync.repository.ImgRepository;
import cn.mayu.yugioh.sync.repository.LinkRepository;
import cn.mayu.yugioh.sync.repository.MagicTrapRepository;
import cn.mayu.yugioh.sync.repository.MonsterRepository;
import cn.mayu.yugioh.sync.repository.PackageInfoRepository;
import cn.mayu.yugioh.sync.repository.PackageRepository;
import cn.mayu.yugioh.sync.repository.RareRepository;
import cn.mayu.yugioh.sync.repository.SyncRecordRepository;
import cn.mayu.yugioh.sync.repository.TypeRepository;
import reactor.core.publisher.Flux;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository repository;

	@Autowired
	private SyncRecordRepository record;
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private MonsterRepository monsterRepository;
	
	@Autowired
	private MagicTrapRepository magicTrapRepository;
	
	@Autowired
	private AdjustRepository adjustRepository;
	
	@Autowired
	private EffectRepository effectRepository;
	
	@Autowired
	private ImgRepository imgRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private RareRepository rareRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private PackageInfoRepository packageInfoRepository;
	
	private static final int HASH_ID_SIZE = 8;

	@Override
	public void saveCardData() throws Exception {
		Flux<CardDataEntity> dataFlux = repository.findByModifyTimeGreaterThanEqual(getLastSyncTime());
		if (dataFlux.count().block() == 0) {
			return;
		}

		dataFlux.subscribe(entity -> {
			try {
				doSync(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private LocalDateTime getLastSyncTime() {
		LocalDateTime lastSyncTime = record.findMaxCreateTime();
		return lastSyncTime == null ? LocalDateTime.of(1970, 1, 1, 1, 1) : lastSyncTime;
	}

	@Transactional
	private void doSync(CardDataEntity entity) {
		Integer cardId = null;
		if (entity.getTypeVal() == 1) { // monster
			MonsterEntity monster = new MonsterEntity();
			BeanUtils.copyProperties(entity, monster, "atk", "def", "pendL", "pendR", "link");
			monster.setHashId(generateHashId(HASH_ID_SIZE));
			monster.setAttribute(indexService.findByNameFromCache(2, entity.getAttribute()));
			monster.setRace(indexService.findByNameFromCache(3, entity.getRace()));
			monster.setAtk(propertieFormat(entity.getAtk()));
			monster.setDef(propertieFormat(entity.getDef()));
			monster.setPendL(propertieFormat(entity.getPendL()));
			monster.setPendR(propertieFormat(entity.getPendR()));
			monster.setLink(propertieFormat(entity.getLink()));
			MonsterEntity saved = monsterRepository.save(monster);
			cardId = saved.getId();
			
			String[] typeSts = entity.getTypeSt().split("\\|");
			List<TypeEntity> monsterTypes = Stream.of(typeSts)
					                              .map(types -> indexService.findByNameFromCache(4, types))
					                              .filter(num -> !num.equals(0))
					                              .map(num -> {
					                            	  			TypeEntity type = new TypeEntity();
					                            	  			type.setCardId(saved.getId());
					                            	  			type.setTypeSt(num);
					                            	  			return type;
					                              }).collect(Collectors.toList());
			typeRepository.saveAll(monsterTypes);
			
			if (monster.getLink() != -1) {
				String linkArrowStr = entity.getLinkArrow().replace("，", ",");
				String[] linkArrows = linkArrowStr.split(",");
				List<LinkEntity> links = Stream.of(linkArrows).map(arrow -> {
					LinkEntity link = new LinkEntity();
					link.setCardId(saved.getId());
					link.setLinkArrow(Integer.valueOf(arrow));
					return link;
				}).collect(Collectors.toList());
				linkRepository.saveAll(links);
			}
		}
		else {// magic/trap
			MagicTrapEntity magicTrap = new MagicTrapEntity();
			BeanUtils.copyProperties(entity, magicTrap);
			magicTrap.setTypeSt(indexService.findByNameFromCache(5, entity.getTypeSt().split("\\|")[1]));
			cardId = magicTrapRepository.save(magicTrap).getId();
		}
		
		if (!entity.getAdjust().equals("")) {
			AdjustEntity adjust = new AdjustEntity();
			adjust.setCardId(cardId);
			adjust.setAdjust(entity.getAdjust());
			adjust.setTypeVal(entity.getTypeVal());
			adjustRepository.save(adjust);
		}
		
		EffectEntity effect = new EffectEntity();
		effect.setCardId(cardId);
		effect.setEffect(StringUtil.effectFormat(entity.getDesc()));
		effect.setEffectNw(StringUtil.effectFormat(entity.getDescNw()));
		effect.setTypeVal(entity.getTypeVal());
		effectRepository.save(effect);
		
		if (entity.getImgUrl() != null) {
			ImgEntity img = new ImgEntity();
			img.setCardId(cardId);
			img.setImg(entity.getImgUrl());
			img.setTypeVal(entity.getTypeVal());
			imgRepository.save(img);
		}
		
		if (entity.getIncludeInfos() != null && entity.getIncludeInfos().size() > 0) {
			for (IncludeInfo info : entity.getIncludeInfos()) {
				RareEntity savedRare = rareRepository.findByShortName(info.getRace());
				if (savedRare == null) {
					RareEntity rare = new RareEntity();
					rare.setShortName(info.getRace());
					savedRare = rareRepository.save(rare);
				}
				
				PackageEntity savedPackage = packageRepository.findByName(info.getPackName());
				if (savedPackage == null) {
					PackageEntity packageEntity = new PackageEntity();
					packageEntity.setName(info.getPackName());
					packageEntity.setSellTime(info.getSellTime());
					packageEntity.setShotName(info.getShortName());
					savedPackage = packageRepository.save(packageEntity);
				}
				
				PackageInfoEntity packageInfo = new PackageInfoEntity();
				packageInfo.setCardId(cardId);
				packageInfo.setNumber(info.getNumber());
				packageInfo.setPackageId(savedPackage.getId());
				packageInfo.setRaceId(savedRare.getId());
				packageInfo.setTypeVal(entity.getTypeVal());
				packageInfoRepository.save(packageInfo);
			}
		}
	}
	
	private Integer propertieFormat(String content) {
		boolean condition = (content == null || content.indexOf("?") != -1 || content.indexOf("？") != -1);
		return condition ? -1 : Integer.valueOf(content);
	}
}
