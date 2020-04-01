package cn.mayu.yugioh.basic.gateway.route;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_route")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
    @Column(name = "setvice_id")
	private String setviceId;
	
    @Column(name = "uri")
	private String uri;
	
    @Column(name = "predicates")
	private String predicates;
	
    @Column(name = "order")
	private Integer order;
	
    @Column(name = "status")
	private Integer status;
}
