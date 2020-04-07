package cn.mayu.yugioh.basic.gateway.route.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "t_filter_args")
public class FilterArgEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "filter_id")
	private Integer filterId;

	@Column(name = "arg_name")
	private String argName;

	@Column(name = "arg_value")
	private String argValue;

	@Column(name = "modify_time")
	@UpdateTimestamp
	private LocalDateTime modifyTime;

	@Column(insertable = false, updatable = false, name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createTime;
}
