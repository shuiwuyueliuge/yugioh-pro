package cn.mayu.yugioh.cardsource.ourocg;

import java.util.concurrent.ThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.cardsource.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.model.PackageDetail;
import cn.mayu.yugioh.cardsource.repository.IncludeRepository;
import cn.mayu.yugioh.cardsource.repository.OurocgRepository;
import cn.mayu.yugioh.cardsource.stream.PackagePublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OurocgService implements Runnable, ThreadFactory {

	private PackageCenter packageCenter;

	private static final String OUROCG_URL = "https://www.ourocg.cn%s";
	
	@Autowired
	private PackagePublisher packagePublisher;

	@Autowired
	public OurocgService(OurocgRepository ourocgRepository, IncludeRepository includeRepository) {
		this.packageCenter = new OurocgDataCenter(ourocgRepository, includeRepository);
		translateOurocgData();
		newThread(this).start();
	}

	private void translateOurocgData() {
		if (!packageCenter.exists())
			return;
		String packageUrl = String.format(OUROCG_URL, "/package");
		try {
			OurocgQueueGuardian.addAll(packageCenter.gainPackageList(packageUrl));
		} catch (Exception e) {
			log.error("GET: [{}] Err: [{}]", packageUrl, e);
		}
	}

	public void publishPackageDetail(String packageUrl, Integer status) throws Exception {
		OurocgQueueGuardian.syncAdd(packageUrl, status);
	}

	@Override
	public void run() {
		while (true) {
			String packageUrl = null;
			try {
				packageUrl = String.format(OUROCG_URL, OurocgQueueGuardian.take());
				PackageDetail packageDetail = packageCenter.gainPackageDetail(packageUrl);
				packagePublisher.publish(packageDetail);
			} catch (Exception e) {
				log.error("GET: [{}] Err: [{}]", packageUrl, e);
			}
		}
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}
}
