package cn.mayu.yugioh.transform.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "t_index")
public class IndexEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", insertable = false, columnDefinition = "VARCHAR DEFAULT ")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "type_index")
    private Integer typeIndex;

    @JsonIgnore
    @Column(name = "create_time", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @Column(name = "img")
    private String img;
}