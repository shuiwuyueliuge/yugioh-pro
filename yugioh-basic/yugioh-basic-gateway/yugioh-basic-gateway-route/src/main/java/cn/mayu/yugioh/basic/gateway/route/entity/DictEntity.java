package cn.mayu.yugioh.basic.gateway.route.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_dict")
public class DictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
    @Column(name = "description")
	private String description;
	
    @Column(name = "type")
	private Integer type;
	
    @Column(name = "value")
	private String value;

    @Column(name = "value_id")
	private Integer valueId;
}
