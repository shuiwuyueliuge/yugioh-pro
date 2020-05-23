package cn.mayu.yugioh.cardsource.ourocg;

import java.util.List;
import cn.mayu.yugioh.cardsource.manager.CardSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
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
import static cn.mayu.yugioh.cardsource.ourocg.DataTypeEnum.*;

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
			PriorityQueueModel model = OurocgQueueGuardian.take();
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
