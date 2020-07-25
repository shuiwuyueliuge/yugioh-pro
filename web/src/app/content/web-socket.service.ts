import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from "rxjs/webSocket";
import { Observable } from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private ws: WebSocketSubject<any> = webSocket("ws://localhost:8081");

  constructor() {
    // this.ws.next({message: 'some message'});
    // this.ws.subscribe(
    //   msg => console.log('message received: ' + msg), 
    //   err => console.log(err), 
    //   () => console.log('complete') 
    // );
  }

  public getWsClient(): WebSocketSubject<any> {
    return this.ws;
  }

  public sendMsg(msg: string): void {
    this.ws.next({ msg: msg });
  }

  public getWsObservable(key: string): Observable<any> {
    return this.ws.multiplex(
      () => ({ type: 'subscribe', tag: key }),
      () => ({ type: 'unsubscribe', tag: key }), 
      message => message.type === key
    );
  }
}
