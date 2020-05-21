package cn.mayu.yugioh.transform.domain.entity;

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
@Table(name = "t_card_type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "type_st")
    private Integer typeSt;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(name = "create_time", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}