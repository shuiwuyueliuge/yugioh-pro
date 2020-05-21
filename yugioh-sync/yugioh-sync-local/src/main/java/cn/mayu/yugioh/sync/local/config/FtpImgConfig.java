package cn.mayu.yugioh.sync.local.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@ConfigurationProperties(prefix = "ftp.img")
@Data
@Configuration
public class FtpImgConfig {
	
	private String host;
	
	private int port;
	
	private String user;
	
	private String psw;
}
