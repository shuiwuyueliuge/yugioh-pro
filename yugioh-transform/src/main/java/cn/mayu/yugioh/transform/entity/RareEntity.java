package cn.mayu.yugioh.transform.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "t_rare")
public class RareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(name = "create_time", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}