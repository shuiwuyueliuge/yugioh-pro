package cn.mayu.yugioh.sync.home.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

	public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";
	
	@Autowired
	private ThreadPoolTaskConfig taskConfig;

	@Bean(name = ASYNC_EXECUTOR_NAME)
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setCorePoolSize(taskConfig.getCoreSize());
		executor.setMaxPoolSize(taskConfig.getMaxSize());
		executor.setQueueCapacity(taskConfig.getQueueSize());
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix(taskConfig.getThreadNamePrefix());
		executor.initialize();
		return executor;
	}
}
