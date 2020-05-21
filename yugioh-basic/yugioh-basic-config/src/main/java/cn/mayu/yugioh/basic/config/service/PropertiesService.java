package cn.mayu.yugioh.basic.config.service;

import java.io.InputStream;
import java.util.List;
import cn.mayu.yugioh.basic.config.model.entity.PropertiesEntity;

public interface PropertiesService {

	List<PropertiesEntity> saveProperties(InputStream stream) throws Exception;
}
