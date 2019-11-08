package cn.mayu.yugioh.sync.entity;

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

    @Column(name = "hash_id", insertable = false, columnDefinition = "VARCHAR DEFAULT ")
    private String hashId;

    @Column(name = "password", insertable = false, columnDefinition = "VARCHAR DEFAULT ")
    private String password;

    @Column(name = "name", insertable = false, columnDefinition = "VARCHAR DEFAULT ")
    private String name;

    @Column(name = "name_ja", insertable = false, columnDefinition = "VARCHAR DEFAULT ")
    private String nameJa;

    @Column(name = "name_en", insertable = false, columnDefinition = "VARCHAR DEFAULT ")
    private String nameEn;

    @Column(name = "name_nw", insertable = false, columnDefinition = "VARCHAR DEFAULT ")
    private String nameNw;

    @Column(name = "locale", insertable = false, columnDefinition = "BIT DEFAULT 3")
    private Integer locale;

    @Column(name = "type_st")
    private Integer typeSt;

    @Column(name = "type_val", insertable = false, columnDefinition = "BIT DEFAULT 3")
    private Integer typeVal;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @Column(name = "create_time", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}