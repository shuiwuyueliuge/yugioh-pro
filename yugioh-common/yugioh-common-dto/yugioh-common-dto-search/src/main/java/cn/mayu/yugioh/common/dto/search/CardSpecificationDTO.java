package cn.mayu.yugioh.common.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 单个条件之间是and关系，集合之间是or关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSpecificationDTO {

    private String keyWord;

    private Integer cardType;

    private String atk;

    private String def;

    private Integer level;

    private Integer pend;

    private Integer link;

    private List<Integer> linkArrow;

    private List<Integer> monsterAttribute;

    private List<Integer> magicTrapType;

    private List<Integer> monsterType;

    private List<Integer> monsterRace;

    private Integer page;

    private Integer size;
}
