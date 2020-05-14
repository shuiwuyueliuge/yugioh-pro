package cn.mayu.yugioh.cardsource.datacenter;

import java.util.List;
import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;

public interface PackageCenter extends DataCenter {

	PackageDetail gainPackageDetail(String resources);
	
	List<String> gainPackageList(String resources);
}