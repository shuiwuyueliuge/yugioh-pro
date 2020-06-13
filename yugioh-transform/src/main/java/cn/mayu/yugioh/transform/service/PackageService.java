package cn.mayu.yugioh.transform.service;

import cn.mayu.yugioh.common.dto.transform.PackageProto;

public interface PackageService {

	Integer save(PackageProto.PackageDetail packageDetail);
}
