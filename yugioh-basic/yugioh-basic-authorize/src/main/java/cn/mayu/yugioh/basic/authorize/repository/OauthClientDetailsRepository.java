package cn.mayu.yugioh.basic.authorize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mayu.yugioh.basic.authorize.model.entity.OauthClientDetails;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, Integer> {

	OauthClientDetails findByClientId(String clientId);
}
