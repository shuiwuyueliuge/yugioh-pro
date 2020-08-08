package cn.mayu.yugioh.common.dto.cardsource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageData extends BaseData {

    private String name;

    private String uri;
}
