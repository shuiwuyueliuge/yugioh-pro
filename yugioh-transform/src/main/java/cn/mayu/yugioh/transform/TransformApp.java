package cn.mayu.yugioh.transform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class TransformApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TransformApp.class);
		app.run(args);
	}

	@Bean
	public TaskScheduler taskScheduler(){
		// Spring提供的定时任务线程池类
		ThreadPoolTaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
		//设定最大可用的线程数目
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}
}
