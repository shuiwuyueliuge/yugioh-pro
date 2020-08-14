package cn.mayu.yugioh.common.dto.cardsource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitData extends BaseData {

    @NotEmpty
    private String url;

    @NotEmpty
    private String time;

    @NotEmpty
    private String ocgTcg;
}
