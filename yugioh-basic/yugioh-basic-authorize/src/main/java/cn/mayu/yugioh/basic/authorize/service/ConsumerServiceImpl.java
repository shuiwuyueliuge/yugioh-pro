package cn.mayu.yugioh.basic.authorize.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.basic.authorize.exception.UserNotFountException;
import cn.mayu.yugioh.basic.authorize.model.dto.UserLoginDto;
import cn.mayu.yugioh.basic.authorize.model.entity.User;
import cn.mayu.yugioh.basic.authorize.repository.UserLoginSpecification;
import cn.mayu.yugioh.basic.authorize.repository.UserRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	private UserRepository consumerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User login(UserLoginDto loginDto) {
		UserLoginSpecification specification = new UserLoginSpecification(loginDto);
		Optional<User> result = consumerRepository.findOne(specification);
		if (!result.isPresent() || !passwordEncoder.matches(loginDto.getPassword(), result.get().getPassword())) {
			throw new UserNotFountException("username or mobile or email or password error");
		}
		
		return result.get();
	}
}
