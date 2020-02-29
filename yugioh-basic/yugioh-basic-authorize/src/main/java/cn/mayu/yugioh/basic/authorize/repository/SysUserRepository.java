package cn.mayu.yugioh.basic.authorize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.mayu.yugioh.basic.authorize.model.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {

	SysUser findByUsername(String username);
}
