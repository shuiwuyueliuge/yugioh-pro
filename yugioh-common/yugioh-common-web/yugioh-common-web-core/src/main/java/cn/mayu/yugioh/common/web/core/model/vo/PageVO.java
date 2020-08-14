package cn.mayu.yugioh.common.web.core.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    private Long count;

    private Integer currentPage;

    private T data;
}