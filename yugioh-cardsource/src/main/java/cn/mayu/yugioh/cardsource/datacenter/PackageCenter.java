package cn.mayu.yugioh.cardsource.datacenter;

import cn.mayu.yugioh.cardsource.model.PackageDetail;

public interface PackageCenter extends DataCenter {

	PackageDetail gainPackageDetail(String resources) throws Exception;
}
