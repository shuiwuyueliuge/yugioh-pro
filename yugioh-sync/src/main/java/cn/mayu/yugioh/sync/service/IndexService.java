package cn.mayu.yugioh.sync.service;

import java.util.List;
import cn.mayu.yugioh.sync.entity.IndexEntity;

public interface IndexService {

	List<IndexEntity> findAll();
}
