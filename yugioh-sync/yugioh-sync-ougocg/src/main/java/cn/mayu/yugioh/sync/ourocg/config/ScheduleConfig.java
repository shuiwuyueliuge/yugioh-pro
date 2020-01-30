package cn.mayu.yugioh.sync.ourocg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import cn.mayu.yugioh.sync.ourocg.property.ScheduleTaskProperty;
import cn.mayu.yugioh.sync.ourocg.service.OurocgDataService;
import cn.mayu.yugioh.sync.ourocg.service.TaskMemoryService;
import cn.mayu.yugioh.sync.ourocg.task.OurocgCrawlingTask;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
	
	@Autowired
	private ScheduleTaskProperty taskProperty;
	
	@Autowired
	private OurocgDataService ourocgDataService;
	
	@Autowired
	private TaskMemoryService memoryService;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(initThreadPoolTaskScheduler());
		taskRegistrar.addTriggerTask(new OurocgCrawlingTask(ourocgDataService, memoryService, taskProperty), initTrigger());
	}
	
	private ThreadPoolTaskScheduler initThreadPoolTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 2);
		taskScheduler.initialize();
		return taskScheduler;
	}
	
	private Trigger initTrigger() {
		return triggerContext -> new CronTrigger(taskProperty.getCorn()).nextExecutionTime(triggerContext);
	}
}
