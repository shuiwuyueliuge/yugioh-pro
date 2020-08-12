package cn.mayu.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.cardsource.basic.datacenter.PackageCenter;
import cn.mayu.yugioh.cardsource.basic.factory.AsyncNoticeManager;
import cn.mayu.yugioh.cardsource.basic.factory.PackageFactory;
import cn.mayu.yugioh.cardsource.basic.service.DataSourceLogService;
import cn.mayu.yugioh.cardsource.basic.stream.PackagePublisher;
import cn.mayu.yugioh.cardsource.basic.stream.WebSocketPublisher;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.transform.PackageDetail;
import cn.mayu.yugioh.common.dto.websocket.PackageAnalyseResult;
import cn.mayu.yugioh.common.dto.websocket.WebSocketSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.EventObject;
import java.util.List;

@Slf4j
@Component
public class OurocgPackageFactory extends AsyncNoticeManager implements PackageFactory {

    private PackageCenter packageCenter;

    @Autowired
    private PackagePublisher packagePublisher;

    public OurocgPackageFactory(DataSourceLogService dataSourceLogService, WebSocketPublisher webSocketPublisher) {
        super(webSocketPublisher);
        this.packageCenter = new OurocgDataCenter(dataSourceLogService);
    }

    @Override
    public void publishPackageDetail(List<String> packageUrls, String channelId, String subscribe) {
        packageUrls.stream().forEach(data -> {
            OurocgEvent ourocgEvent = new OurocgEvent(channelId, subscribe, data);
            OurocgTaskExecutor.exec(() -> gainDetailAsync(ourocgEvent));
        });
    }

    @Override
    public List<PackageData> gainPackageList() {
        String packageUrl = String.format(packageCenter.url(), "/package");
        return packageCenter.gainPackageList(packageUrl);
    }

    @Override
    public Object getStartResult(EventObject eventObject) {
        OurocgEvent event = (OurocgEvent) eventObject;
        return new PackageAnalyseResult(event.getData(), 65);
    }

    @Override
    public Object getEndResult(EventObject eventObject) {
        OurocgEvent event = (OurocgEvent) eventObject;
        return new PackageAnalyseResult(event.getData(), 100);
    }

    @Override
    public void doAsync(EventObject eventObject) {
        OurocgEvent event = (OurocgEvent) eventObject;
        WebSocketSource source = (WebSocketSource) event.getSource();
        String url = String.format(packageCenter.url(), event.getData());
        PackageDetail packageDetail = packageCenter.gainPackageDetail(url);
        if (packageDetail.getEnPackages().size() > 0) {
            publishPackageDetail(packageDetail.getEnPackages(), source.getChannelId(), source.getSubscribe());
        }

        packagePublisher.publish(packageDetail);
    }
}