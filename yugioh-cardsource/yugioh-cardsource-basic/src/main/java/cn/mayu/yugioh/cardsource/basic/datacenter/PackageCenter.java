package cn.mayu.yugioh.cardsource.basic.datacenter;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.transform.PackageDetail;
import java.util.List;

public interface PackageCenter extends DataCenter {

	PackageDetail gainPackageDetail(String resources);
	
	List<PackageData> gainPackageList(String resources);
}
