const SLIDE_MENU: Resources =  { uri: "assets/showcase/data/slidemenu.json", description: "slidemenu" };

const PACKAGE: Resources =  { uri: "assets/showcase/data/package.json", description: "package" };

const CARD_SOURCE: Resources =  { uri: "assets/showcase/data/cardsource.json", description: "cardsource" };

interface Resources {

    uri: string;

    description: string;
}

export { SLIDE_MENU, PACKAGE, CARD_SOURCE}