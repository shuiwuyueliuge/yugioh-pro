package cn.mayu.yugioh.sync.local.entity;

import java.time.LocalDate;
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
@Table(name = "t_forbidden")
public class ForbiddenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "type_val")
    private Integer typeVal;

    @Column(name = "limit_val")
    private Integer limitVal;

    @Column(name = "limit_time")
    private LocalDate limitTime;

    @Column(name = "type")
    private Integer type;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(insertable = false, updatable = false, name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}