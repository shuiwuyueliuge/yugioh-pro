const SLIDE_MENU: Resources =  { uri: "assets/showcase/data/slidemenu.json", description: "slidemenu" };

//const PACKAGE: Resources =  { uri: "assets/showcase/data/package.json", description: "package" };

//const CARD_SOURCE: Resources =  { uri: "assets/showcase/data/cardsource.json", description: "cardsource" };

const CARD_SOURCE: Resources =  { uri: "http://127.0.0.1:9700/sync/source", description: "cardsource" };

const PACKAGE: Resources =  { uri: "http://127.0.0.1:9700/sync/package/", description: "cardsource" };

const PACKAGE_PUBLISH: Resources =  { uri: "http://127.0.0.1:9700/sync/package/", description: "cardsource" };

interface Resources {

    uri: string;

    description: string;
}

export { SLIDE_MENU, PACKAGE, CARD_SOURCE, PACKAGE_PUBLISH}