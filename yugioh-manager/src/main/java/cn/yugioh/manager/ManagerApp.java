package cn.yugioh.manager;

import cn.yugioh.common.facade.search.SearchCardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagerApp {

	@Autowired
	private SearchCardFacade searchCardFacade;
	
	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication(ManagerApp.class);
		app.run(args);
	}
}
