package cn.mayu.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.cardsource.basic.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OurocgFactory implements CardSourceFactory {

    @Autowired
    private OurocgLimitFactory limitFactory;

    @Autowired
    private OurocgPackageFactory packageFactory;

    @Override
    public PackageFactory initPackageFactory() {
        return packageFactory;
    }

    @Override
    public LimitFactory initLimitFactory() {
        return limitFactory;
    }

    @Override
    public CardSourceEnum getCardSource() {
        return CardSourceEnum.OUROCG;
    }
}
