package cn.mayu.yugioh.common.web.core.intercept.web;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Pageable<T> extends ResponseBody {

    private Long count;

    private Integer currentPage;

    public Pageable(Long count, Integer currentPage, T data, String traceId) {
        super(data, traceId);
        this.count = count;
        this.currentPage = currentPage;
    }
}

