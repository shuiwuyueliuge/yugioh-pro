package cn.mayu.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.cardsource.basic.service.DataSourceLogService;
import cn.mayu.yugioh.cardsource.basic.stream.WebSocketPublisher;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.transform.PackageDetail;
import cn.mayu.yugioh.cardsource.basic.datacenter.LimitCenter;
import cn.mayu.yugioh.cardsource.basic.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.basic.manager.CardSourceEnum;
import cn.mayu.yugioh.cardsource.basic.stream.LimitPublisher;
import cn.mayu.yugioh.cardsource.basic.stream.PackagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static cn.mayu.yugioh.cardsource.core.ourocg.DataTypeEnum.LIMIT;
import static cn.mayu.yugioh.cardsource.core.ourocg.DataTypeEnum.PACKAGE;
import static cn.mayu.yugioh.cardsource.core.ourocg.OurocgQueueGuardian.addAll;
import static cn.mayu.yugioh.cardsource.core.ourocg.OurocgQueueGuardian.addOne;

@Slf4j
@Service
public class OurocgServiceImpl implements OurocgService {

	private PackageCenter packageCenter;
	
	private LimitCenter limitCenter;

	@Autowired
	WebSocketPublisher webSocketPublisher;

	private static final String OUROCG_URL = "https://www.ourocg.cn%s";

	@Autowired
	private PackagePublisher packagePublisher;
	
	@Autowired
	private LimitPublisher limitPublisher;

	@Autowired
	public OurocgServiceImpl(DataSourceLogService dataSourceLogService) {
		OurocgDataCenter ourocgDataCenter = new OurocgDataCenter(dataSourceLogService);
		this.packageCenter = ourocgDataCenter;
		this.limitCenter = ourocgDataCenter;
	}

	@Override
	public void publishPackageDetail(List<String> packageUrls, Integer priority, String channelId) {
		addAll(packageUrls, priority, PACKAGE, channelId);
	}

	@Override
	public void publishLimitDetail(String LimitUrl, Integer priority) {
		addOne(LimitUrl, priority, LIMIT);
	}

	@Override
	public List<PackageData> gainPackageList() {
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
			try {
				OurocgQueueGuardian.PriorityQueueModel model = OurocgQueueGuardian.take();
				if (model == null) continue;
				if (model.getDataType() == PACKAGE) {
					webSocketPublisher.publish("{\"channelId\":\"" + model.getChannelId() + "\",\"progress\":65,\"packageName\":\"" + model.getData() + "\"}");
					String url = String.format(OUROCG_URL, model.getData());
					PackageDetail packageDetail = packageCenter.gainPackageDetail(url);
					packagePublisher.publish(packageDetail);
					webSocketPublisher.publish("{\"channelId\":\"" + model.getChannelId() + "\",\"progress\":100,\"packageName\":\"" + model.getData() + "\"}");
				}

				if (model.getDataType() == LIMIT) {
					LimitDetail limitDetail = limitCenter.gainLimitDetail(model.getData());
					limitPublisher.publish(limitDetail);
				}
			} catch (Exception e) {
				log.error("{}", e);
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
	public void run(String... args) {
		newThread(this).start();
	}
}
