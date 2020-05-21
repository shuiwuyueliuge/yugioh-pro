package cn.mayu.yugioh.cardsource.ourocg.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OurocgLinkArrowEnum {

    ARROW_1(1, "↙"),
    ARROW_2(2, "↓"),
    ARROW_3(3, "↘"),
    ARROW_4(4, "←"),
    ARROW_6(6, "→"),
    ARROW_7(7, "↖"),
    ARROW_8(8, "↑"),
    ARROW_9(9, "↗");

    private int num;

    private String arrow;

    public static String getArrow(int num) {
        for (OurocgLinkArrowEnum arrow : values()) {
            if (arrow.num == num) return arrow.arrow;
        }

        return "";
    }
}
