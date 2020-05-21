package cn.mayu.yugioh.basic.authorize.service;

import cn.mayu.yugioh.basic.authorize.model.dto.UserLoginDto;
import cn.mayu.yugioh.basic.authorize.model.entity.User;

public interface ConsumerService {

	User login(UserLoginDto loginDto);
}
