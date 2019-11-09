package cn.mayu.yugioh.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import cn.mayu.yugioh.sync.entity.IndexEntity;
import cn.mayu.yugioh.sync.service.IndexService;

@SpringBootApplication
@EnableScheduling
public class App implements CommandLineRunner {
	
	@Autowired
	private RedisTemplate<String, IndexEntity> template;
	
	@Autowired
	private IndexService indexService;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		indexService.findAll().stream().forEach(entity -> {
			template.opsForSet().add("index:type:" + entity.getType(), entity);
		});
	}
}

