package cn.mayu.yugioh.cardsource.core.ourocg.html;

import cn.mayu.yugioh.cardsource.basic.html.HtmlHandler;
import cn.mayu.yugioh.cardsource.core.ourocg.model.Include;
import cn.mayu.yugioh.cardsource.core.ourocg.model.LimitInfo;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import java.util.List;

public class OurocgHtmlHandlers {

    public static HtmlHandler<String> cardDataTranslater = new CardDataHtmlHandler();

    public static HtmlHandler<Include> includeTranslater = new IncludeInfoHandler();

    public static HtmlHandler<List<PackageData>> packageListTranslater = new PackageListHandler();

    public static HtmlHandler<LimitInfo> limitDataTranslater = new LimitDataHandler();

    public static HtmlHandler<List<String>> limitListTranslater = new LimitListHandler();
}
