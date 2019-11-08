package cn.mayu.yugioh.sync.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_sync_record")
public class SyncRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "status", insertable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Integer status;

    @Column(name = "update_content")
    private String updateContent;

    @Column(name = "create_time", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}