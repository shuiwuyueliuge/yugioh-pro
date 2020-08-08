package cn.mayu.yugioh.common.dto.cardsource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitData extends BaseData {

    private String url;

    private String time;

    private String ocgTcg;
}
