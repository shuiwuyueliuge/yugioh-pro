//package com.web.test;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import cn.mayu.yugioh.security.application.config.ClientDetailsServiceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.token.*;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//import org.springframework.security.web.authentication.*;
//import org.springframework.social.security.SocialUser;
//import org.springframework.social.security.SocialUserDetails;
//import org.springframework.social.security.SocialUserDetailsService;
//import org.springframework.stereotype.Service;
//import javax.sql.DataSource;
//
//@Service
//public class UserService implements SocialUserDetailsService, UserDetailsService {
//
//    @Override
//    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
//        return new SocialUser("test", new BCryptPasswordEncoder().encode("123"), authorities());
//    }
//
//    private Collection<GrantedAuthority> authorities() {
//        List<GrantedAuthority> authList = new ArrayList<>();
//        List<String> list = new ArrayList<>();
//        list.stream().forEach(role -> authList.add(new SimpleGrantedAuthority(role)));
//        return authList;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
//
//    @Bean
//    public ClientDetailsServiceBuilder jdbc(DataSource dataSource) {
//        return () -> new JdbcClientDetailsService(dataSource);
//    }
//
//    @Bean
//    public TokenStore redisTokenStore() {
//        return new InMemoryTokenStore();
//    }
//
//    @Bean
//    public AccessTokenConverter jwtAccessTokenConverter() {
//        return new DefaultAccessTokenConverter();
//    }
//
//    @Bean
//    public TokenEnhancerChain enhancerChain() {
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        return enhancerChain;
//    }
//
//    @Bean
//    public AuthenticationFailureHandler aa() {
//        return new SimpleUrlAuthenticationFailureHandler();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler aaa() {
//        return new SavedRequestAwareAuthenticationSuccessHandler();
//    }
//}