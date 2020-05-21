package cn.mayu.yugioh.basic.authorize.service;

import cn.mayu.yugioh.basic.authorize.model.dto.UserLoginDto;
import cn.mayu.yugioh.basic.authorize.model.entity.SysUser;

public interface SysUserService {

	SysUser login(UserLoginDto loginDto);
}
