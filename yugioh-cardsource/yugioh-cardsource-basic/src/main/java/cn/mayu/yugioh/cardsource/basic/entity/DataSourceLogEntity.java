package cn.mayu.yugioh.cardsource.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "packages")
public class DataSourceLogEntity {

    @Id
    private String id;

    private String dataSourceType;

    private String logType;

    private Object data;

    private LocalDateTime createTime = LocalDateTime.now();
}
