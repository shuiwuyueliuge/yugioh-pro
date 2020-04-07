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

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "t_filter")
public class FilterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
    @Column(name = "route_id")
	private Integer route_id;
	
    @Column(name = "filter_name")
	private int filterName;
    
    @Column(name = "modify_time")
    @UpdateTimestamp
    private LocalDateTime modifyTime;
    
    @Column(insertable = false, updatable = false, name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "filter_id")
    private List<FilterArgEntity> filterArgEntities;
}
