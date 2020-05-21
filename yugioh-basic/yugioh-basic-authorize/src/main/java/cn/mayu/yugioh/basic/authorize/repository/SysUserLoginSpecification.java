package cn.mayu.yugioh.basic.authorize.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import cn.mayu.yugioh.basic.authorize.model.dto.UserLoginDto;
import cn.mayu.yugioh.basic.authorize.model.entity.SysUser;
import cn.mayu.yugioh.basic.authorize.model.entity.User;
import cn.mayu.yugioh.basic.authorize.model.entity.UserRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SysUserLoginSpecification implements Specification<SysUser> {
	
	private static final long serialVersionUID = 1L;
	
	private UserLoginDto loginDto;

	@Override
	public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate usernamePredicate = criteriaBuilder.equal(root.get("username"), loginDto.getUsername());
		Predicate mobilePredicate = criteriaBuilder.equal(root.get("mobile"), loginDto.getUsername());
		Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), loginDto.getUsername());
		Predicate orPredicate = criteriaBuilder.or(usernamePredicate, mobilePredicate, emailPredicate);
		Join<User, UserRegistration> join = root.join("userRegistration", JoinType.INNER);
		Predicate appIdPredicate =  criteriaBuilder.equal(join.get("clientId"), loginDto.getClientId());
		return criteriaBuilder.and(orPredicate, appIdPredicate);
	}
}
