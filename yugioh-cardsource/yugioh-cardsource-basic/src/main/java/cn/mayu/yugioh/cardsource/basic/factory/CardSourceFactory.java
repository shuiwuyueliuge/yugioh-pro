package cn.mayu.yugioh.cardsource.basic.factory;

public interface CardSourceFactory {

    PackageFactory initPackageFactory();

    LimitFactory initLimitFactory();

    CardSourceEnum getCardSource();
}
