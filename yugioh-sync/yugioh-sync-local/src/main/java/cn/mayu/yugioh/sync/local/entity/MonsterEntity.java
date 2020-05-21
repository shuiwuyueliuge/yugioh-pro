package cn.mayu.yugioh.sync.local.entity;

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
@Table(name = "t_monster")
public class MonsterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hash_id")
    private String hashId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "name_ja")
    private String nameJa;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "name_nw")
    private String nameNw;

    @Column(name = "locale")
    private Integer locale;

    @Column(name = "level")
    private Integer level;

    @Column(name = "attribute")
    private Integer attribute;

    @Column(name = "race")
    private Integer race;

    @Column(name = "atk")
    private Integer atk;

    @Column(name = "def")
    private Integer def;

    @Column(name = "pend_l")
    private Integer pendL;

    @Column(name = "pend_r")
    private Integer pendR;

    @Column(name = "link")
    private Integer link;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(name = "create_time", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}