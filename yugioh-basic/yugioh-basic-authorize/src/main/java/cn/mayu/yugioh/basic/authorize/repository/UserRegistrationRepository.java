package cn.mayu.yugioh.basic.authorize.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mayu.yugioh.basic.authorize.model.entity.UserRegistration;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer> {

}
