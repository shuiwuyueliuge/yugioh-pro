package cn.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.transform.PackageDetail;
import cn.yugioh.cardsource.basic.datacenter.LimitCenter;
import cn.yugioh.cardsource.basic.datacenter.PackageCenter;
import cn.yugioh.cardsource.basic.manager.CardSourceEnum;
import cn.yugioh.cardsource.basic.stream.LimitPublisher;
import cn.yugioh.cardsource.basic.stream.PackagePublisher;
import cn.yugioh.cardsource.core.ourocg.repository.OurocgIncludeRepository;
import cn.yugioh.cardsource.core.ourocg.repository.OurocgLimitRepository;
import cn.yugioh.cardsource.core.ourocg.repository.OurocgPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static cn.yugioh.cardsource.core.ourocg.DataTypeEnum.LIMIT;
import static cn.yugioh.cardsource.core.ourocg.DataTypeEnum.PACKAGE;
import static cn.yugioh.cardsource.core.ourocg.OurocgQueueGuardian.addAll;
import static cn.yugioh.cardsource.core.ourocg.OurocgQueueGuardian.addOne;


@Service
public class OurocgServiceImpl implements OurocgService {

	private PackageCenter packageCenter;
	
	private LimitCenter limitCenter;

	private static final String OUROCG_URL = "https://www.ourocg.cn%s";

	@Autowired
	private PackagePublisher packagePublisher;
	
	@Autowired
	private LimitPublisher limitPublisher;

	@Autowired
	public OurocgServiceImpl(OurocgPackageRepository ourocgRepository,
							 OurocgIncludeRepository includeRepository,
							 OurocgLimitRepository limitRepository) {
		OurocgDataCenter ourocgDataCenter = new OurocgDataCenter(ourocgRepository, includeRepository, limitRepository);
		this.packageCenter = ourocgDataCenter;
		this.limitCenter = ourocgDataCenter;
	}

	@Override
	public void publishPackageDetail(List<String> packageUrls, Integer priority) {
		addAll(packageUrls, priority, PACKAGE);
	}

	@Override
	public void publishLimitDetail(String LimitUrl, Integer priority) {
		addOne(LimitUrl, priority, LIMIT);
	}

	@Override
	public List<String> gainPackageList() {
		String packageUrl = String.format(OUROCG_URL, "/package");
		return packageCenter.gainPackageList(packageUrl);
	}

	@Override
	public List<String> gainLimitList(String resources) {
		return limitCenter.gainLimitList(resources);
	}

	@Override
	public CardSourceEnum getCardSourceType() {
		return CardSourceEnum.OUROCG;
	}

	@Override
	public void run() {
		while (true) {
			OurocgQueueGuardian.PriorityQueueModel model = OurocgQueueGuardian.take();
			if (model == null) continue;
			if (model.getDataType() == PACKAGE) {
				String url = String.format(OUROCG_URL, model.getData());
				PackageDetail packageDetail = packageCenter.gainPackageDetail(url);
				packagePublisher.publish(packageDetail);
			}

			if (model.getDataType() == LIMIT) {
				LimitDetail limitDetail = limitCenter.gainLimitDetail(model.getData());
				limitPublisher.publish(limitDetail);
			}
		}
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setName("ourocg-package-thread");
		return thread;
	}

	@Override
	public void run(String... args) throws Exception {
		newThread(this).start();
	}
}
