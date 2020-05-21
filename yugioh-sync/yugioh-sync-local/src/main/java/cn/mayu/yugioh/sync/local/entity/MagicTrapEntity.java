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
@Table(name = "t_magic_trap")
public class MagicTrapEntity {
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

    @Column(name = "type_st")
    private Integer typeSt;

    @Column(name = "type_val")
    private Integer typeVal;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(insertable = false, updatable = false, name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}