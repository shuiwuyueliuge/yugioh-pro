package cn.mayu.yugioh.transform.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "t_forbidden")
@SQLInsert(sql = "INSERT IGNORE INTO t_forbidden(card_id, card_name, limit_time, limit_val, modify_time, type_val, id) values (?, ?, ?, ?, ?, ?, ?)")
public class ForbiddenEntity {

    @Id
    @GenericGenerator(name="generatsr", strategy="increment")
    @GeneratedValue(generator="generatsr")
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_name")
    private String cardName;
    
    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "type_val")
    private Integer typeVal;

    @Column(name = "limit_val")
    private Integer limitVal;

    @Column(name = "limit_time")
    private LocalDate limitTime;

    @Column(name = "region")
    private String region;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(insertable = false, updatable = false, name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}