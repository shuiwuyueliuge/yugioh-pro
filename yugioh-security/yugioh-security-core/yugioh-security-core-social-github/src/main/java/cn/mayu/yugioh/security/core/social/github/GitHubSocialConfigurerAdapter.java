package cn.mayu.yugioh.security.core.social.github;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import cn.mayu.org.yugioh.security.core.base.social.SocialJdbcUsersConnectionRepository;
import cn.mayu.org.yugioh.security.core.base.social.SocialProperty;

@EnableSocial
public class GitHubSocialConfigurerAdapter extends SocialConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SocialProperty socialProperty;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer,
			Environment environment) {//"github", "9ff4b8589e5765cc2ceb", "a58e09c9853ec70cc4536575d28ceb4fc7dc1bcc"
		connectionFactoryConfigurer.addConnectionFactory(new GitHubConnectionFactory("github", "9ff4b8589e5765cc2ceb", "a58e09c9853ec70cc4536575d28ceb4fc7dc1bcc"));
	}
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		SocialJdbcUsersConnectionRepository jdbcUsersConnectionRepository = new SocialJdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		jdbcUsersConnectionRepository.setTablePrefix(socialProperty.getTablePrefix());
		return jdbcUsersConnectionRepository;
	}
	
	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}
}
