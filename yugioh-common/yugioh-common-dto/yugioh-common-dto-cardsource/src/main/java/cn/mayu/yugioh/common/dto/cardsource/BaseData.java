package cn.mayu.yugioh.common.dto.cardsource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData {

    private List<String> urls;

    private String channelId;

    private String subscribe;
}
