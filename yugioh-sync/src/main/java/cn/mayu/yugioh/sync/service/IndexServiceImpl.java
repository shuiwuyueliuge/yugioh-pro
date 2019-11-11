package cn.mayu.yugioh.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.sync.entity.IndexEntity;
import cn.mayu.yugioh.sync.repository.IndexRepository;

//@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private IndexRepository indexRepository;

	@Override
	public void indexCache() {
		indexRepository.findAll().stream().forEach(entity -> {
		});
	}
}
