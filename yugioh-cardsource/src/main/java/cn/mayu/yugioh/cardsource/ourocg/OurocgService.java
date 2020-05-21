package cn.mayu.yugioh.cardsource.ourocg;

import java.util.List;
import java.util.concurrent.ThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.cardsource.datacenter.LimitCenter;
import cn.mayu.yugioh.cardsource.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.ourocg.repository.OurocgIncludeRepository;
import cn.mayu.yugioh.cardsource.ourocg.repository.OurocgLimitRepository;
import cn.mayu.yugioh.cardsource.ourocg.repository.OurocgPackageRepository;
import cn.mayu.yugioh.cardsource.stream.LimitPublisher;
import cn.mayu.yugioh.cardsource.stream.PackagePublisher;
import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;
import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;
import static cn.mayu.yugioh.cardsource.ourocg.OurocgQueueGuardian.*;

@Service
public class OurocgService implements Runnable, ThreadFactory, CommandLineRunner {

	private PackageCenter packageCenter;
	
	private LimitCenter limitCenter;

	private static final String OUROCG_URL = "https://www.ourocg.cn%s";

	@Autowired
	private PackagePublisher packagePublisher;
	
	@Autowired
	private LimitPublisher limitPublisher;

	@Autowired
	public OurocgService(OurocgPackageRepository ourocgRepository, 
						 OurocgIncludeRepository includeRepository, 
						 OurocgLimitRepository limitRepository) {
		OurocgDataCenter ourocgDataCenter = new OurocgDataCenter(ourocgRepository, includeRepository, limitRepository);
		this.packageCenter = ourocgDataCenter;
		this.limitCenter = ourocgDataCenter;
	}

	private void translateOurocgData() {
		if (!packageCenter.exists())
			return;
		String packageUrl = String.format(OUROCG_URL, "/package");
		addAll(packageCenter.gainPackageList(packageUrl));
		String limitLatestUrl = String.format(OUROCG_URL, "/Limit-Latest");
		List<String> urls = limitCenter.gainLimitList(limitLatestUrl);
		urls.stream().forEach(data -> {
			limitPublisher.publish(limitCenter.gainLimitDetail(data));
		});
		
		newThread(this).start();
	}

	public void publishPackageDetail(String packageUrl, Integer status) {
		syncAdd(packageUrl, status);
	}
	
	public void publishLimitDetail(String LimitUrl) {
		LimitDetail limitDetail =  limitCenter.gainLimitDetail(LimitUrl);
		limitPublisher.publish(limitDetail);
	}

	@Override
	public void run() {
		while (true) {
			String packageUrl = String.format(OUROCG_URL, OurocgQueueGuardian.take());
			PackageDetail packageDetail = packageCenter.gainPackageDetail(packageUrl);
			packagePublisher.publish(packageDetail);
		}
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setName("ourocg-package-thread");
		thread.setDaemon(true);
		return thread;
	}

	@Override
	public void run(String... args) throws Exception {
		translateOurocgData();
	}
}
