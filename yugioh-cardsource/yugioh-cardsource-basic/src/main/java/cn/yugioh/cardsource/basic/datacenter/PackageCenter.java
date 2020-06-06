package cn.yugioh.cardsource.basic.datacenter;

import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;

import java.util.List;

public interface PackageCenter extends DataCenter {

	PackageDetail gainPackageDetail(String resources);
	
	List<String> gainPackageList(String resources);
}
