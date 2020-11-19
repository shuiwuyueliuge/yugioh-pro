package cn.mayu.yugioh.security.core.social.github;

/**
 * @see https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 */
public interface GitHub {

	GitHubUserInfo getUserInfo() throws Exception;
}
