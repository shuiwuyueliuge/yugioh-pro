package cn.mayu.yugioh.security.core.social.github;

import cn.mayu.yugioh.common.core.util.JsonUtil;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

public class GitHubOAuth2ApiBinding extends AbstractOAuth2ApiBinding implements GitHub {
	
	private String token;
	
	private static final String USER_INFO_URL = "https://api.github.com/user?access_token=%s"; 
	
	protected GitHubOAuth2ApiBinding(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.token = accessToken;
	}

	@Override
	public GitHubUserInfo getUserInfo() throws Exception {
		String result = this.getRestTemplate().getForObject(String.format(USER_INFO_URL, token), String.class);
		return JsonUtil.readValue(result, GitHubUserInfo.class);
	}
}
