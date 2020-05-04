package cn.mayu.yugioh.cardsource.ourocg;

import org.springframework.stereotype.Service;
import cn.mayu.yugioh.cardsource.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.model.PackageDetail;
import cn.mayu.yugioh.cardsource.repository.OurocgRepository;

@Service
public class OurocgService {
	
	private PackageCenter packageCenter;
	
	public OurocgService(OurocgRepository ourocgRepository) {
		this.packageCenter = new OurocgDataCenter(ourocgRepository);
	}
	
	public void publishPackageDetail(String packageUrl) throws Exception {
		if (!packageCenter.exists()) return;
		PackageDetail packageDetail = packageCenter.gainPackageDetail(packageUrl);
		
	}
}
