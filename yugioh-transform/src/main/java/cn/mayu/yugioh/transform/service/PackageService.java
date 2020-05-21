package cn.mayu.yugioh.transform.service;

import cn.mayu.yugioh.common.dto.cardsource.PackageProto;

public interface PackageService {

	Integer save(PackageProto.PackageDetail packageDetail);
}
