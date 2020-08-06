const SLIDE_MENU: Resources =  { uri: 'assets/showcase/data/slidemenu.json', description: '' };

//const PACKAGE: Resources =  { uri: 'assets/showcase/data/package.json', description: '' };

//const CARD_SOURCE: Resources =  { uri: 'assets/showcase/data/cardsource.json', description: '' };

const CARD_SOURCE: Resources =  { uri: 'http://127.0.0.1:9700/sync/source', description: '' };

const PACKAGE: Resources =  { uri: 'http://127.0.0.1:9700/sync/package/', description: '' };

const PACKAGE_PUBLISH: Resources =  { uri: 'http://127.0.0.1:9700/sync/package/', description: '' };

const WEB_SOCKET: Resources =  { uri: 'ws://localhost:8081', description: '' };

interface Resources {

    uri: string;

    description: string;
}

export { SLIDE_MENU, PACKAGE, CARD_SOURCE, PACKAGE_PUBLISH, WEB_SOCKET }