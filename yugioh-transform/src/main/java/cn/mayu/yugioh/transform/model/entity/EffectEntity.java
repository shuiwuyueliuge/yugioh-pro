package cn.mayu.yugioh.transform.model.entity;

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
@Table(name = "t_card_effect")
public class EffectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "type_val")
    private Integer typeVal;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(name = "create_time", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
    
    @Column(name = "effect")
    private String effect;

    @Column(name = "effect_nw")
    private String effectNw;
    
    @Column(name = "effect_ja")
    private String effectJa;
    
    @Column(name = "effect_en")
    private String effectEn;
}