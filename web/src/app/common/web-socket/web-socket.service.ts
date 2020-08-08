import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from "rxjs/webSocket";
import { Observable } from "rxjs/internal/Observable";
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private ws: WebSocketSubject<any> = webSocket(environment.webSocket);

  constructor() {
  }

  public getWsClient(): WebSocketSubject<any> {
    return this.ws;
  }

  public sendMsg(msg: string): void {
    this.ws.next({ msg: msg });
  }

  public getWsObservable(key: string): Observable<any> {
    return this.ws.multiplex(
      () => ({ subscribe: key }),
      () => ({ unsubscribe: key }),
      message => message.source.subscribe === key
    );
  }
}
