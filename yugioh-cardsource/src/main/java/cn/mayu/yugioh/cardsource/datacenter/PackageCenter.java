package cn.mayu.yugioh.cardsource.datacenter;

import java.util.List;
import cn.mayu.yugioh.cardsource.model.PackageDetail;

public interface PackageCenter extends DataCenter {

	PackageDetail gainPackageDetail(String resources) throws Exception;
	
	List<String> gainPackageList(String resources) throws Exception;
}
