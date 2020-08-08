package cn.mayu.yugioh.common.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitAnalyseResult {

    private String LimitName;

    private Integer progress;
}
