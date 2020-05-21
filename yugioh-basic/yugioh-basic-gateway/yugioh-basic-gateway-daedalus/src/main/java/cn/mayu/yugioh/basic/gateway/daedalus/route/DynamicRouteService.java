package cn.mayu.yugioh.basic.gateway.daedalus.route;

import java.util.concurrent.ThreadFactory;
import org.springframework.context.ApplicationEventPublisherAware;

public interface DynamicRouteService extends ApplicationEventPublisherAware, Runnable, ThreadFactory {

    void loadRouteTask();
}
