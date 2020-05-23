package cn.mayu.yugioh.common.core.api;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum PriorityEnum {

    HIGH(2), MEDIUM(1), LOW(0);

    int level;
}
