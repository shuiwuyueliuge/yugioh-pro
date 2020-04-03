package cn.mayu.yugioh.basic.gateway.route.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Data
@Entity
@Table(name = "t_route")
@DynamicInsert
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
    @Column(name = "service_id")
	private String serviceId;
	
    @Column(name = "uri")
	private String uri;
	
    @Column(name = "sort")
	private Integer sort;
	
    @Column(name = "state", columnDefinition="tinyint default 0")
	private Integer state;
    
    @Column(name = "modify_time")
    @UpdateTimestamp
    private LocalDateTime modifyTime;
    
    @Column(insertable = false, updatable = false, name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private List<PredicatesEntity> predicatesEntities;
}
