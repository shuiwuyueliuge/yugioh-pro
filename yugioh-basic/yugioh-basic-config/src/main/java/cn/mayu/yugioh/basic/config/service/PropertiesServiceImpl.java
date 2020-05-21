package cn.mayu.yugioh.basic.config.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.basic.config.model.entity.PropertiesEntity;
import cn.mayu.yugioh.basic.config.parse.ProfileParser;
import cn.mayu.yugioh.basic.config.parse.PropertiesProfileParser;
import cn.mayu.yugioh.basic.config.repository.PropertiesRepository;

@Service
public class PropertiesServiceImpl implements PropertiesService {
	
	@Autowired
	private PropertiesRepository propertiesRepository;

	@Override
	public List<PropertiesEntity> saveProperties(InputStream stream) throws Exception {
		ProfileParser profileParser = new PropertiesProfileParser();
		Map<Object, Object> map = profileParser.parse(stream);
		List<PropertiesEntity> list = map.entrySet().stream().filter(entity -> !entity.getKey().equals("spring.application.name")).map(entity -> {
			PropertiesEntity propertiesEntity = new PropertiesEntity();
			propertiesEntity.setKey(entity.getKey().toString());
			propertiesEntity.setValue(entity.getValue().toString());
			propertiesEntity.setApplication(map.get("spring.application.name").toString());
			propertiesEntity.setProfile("stage");
			propertiesEntity.setLabel("develop");
			propertiesEntity.setAutoRefresh(0);
			return propertiesEntity;
		}).collect(Collectors.toList());
		
		return propertiesRepository.saveAll(list);
	}
}
