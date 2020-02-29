package cn.mayu.yugioh.basic.authorize.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.basic.authorize.exception.UserNotFountException;
import cn.mayu.yugioh.basic.authorize.model.dto.UserLoginDto;
import cn.mayu.yugioh.basic.authorize.model.entity.SysUser;
import cn.mayu.yugioh.basic.authorize.repository.SysUserLoginSpecification;
import cn.mayu.yugioh.basic.authorize.repository.SysUserRepository;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserRepository sysUserRepository;

	//@Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public SysUser login(UserLoginDto loginDto) {
		SysUserLoginSpecification specification = new SysUserLoginSpecification(loginDto);
		Optional<SysUser> result = sysUserRepository.findOne(specification);
		if (!result.isPresent() || !passwordEncoder.matches(loginDto.getPassword(), result.get().getPassword())) {
			throw new UserNotFountException("username or mobile or email or password error");
		}
		
		return result.get();
	}
}
