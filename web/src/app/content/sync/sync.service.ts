import { Injectable } from '@angular/core';
import { Package } from './package';
import { CardSource } from './cardsource';
import { HttpClient, HttpErrorResponse  } from '@angular/common/http';
import { PACKAGE, CARD_SOURCE } from '../../url';
import { throwError, EMPTY } from 'rxjs';
import { retry, catchError, timeout } from 'rxjs/operators';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class SyncService {

  constructor(private http: HttpClient, private messageService: MessageService) { }

  public async getPackage(cardSource: number): Promise<Package[]> {
    let uri = PACKAGE.uri;
    const res = await this.http.get<any>(uri).pipe(timeout(30000), catchError(this.handleError)).toPromise();
    if (localStorage.msg != null) {
      this.messageService.add({ severity: 'error', summary: localStorage.msg, detail: uri, closable: false });
      localStorage.removeItem('msg');
      return [];
    }
    
    return (<Package[]> res.data);
  }

  public async getCardSources(): Promise<CardSource[]> {
    let uri = CARD_SOURCE.uri;
    const res = await this.http.get<any>(uri).pipe(timeout(30000), catchError(this.handleError)).toPromise();
    return (<CardSource[]> res.data);
  }

  public handleError(error: HttpErrorResponse, caught: any) {
    // let errorMessage = 'Unknown error!';
    // if (error.error instanceof ErrorEvent) {
    //   errorMessage = `Error: ${error.error.message}`;
    // } else {
    //   errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    // }
    localStorage["msg"] = `${error.status} ${error.statusText}`;
    return EMPTY;
  }
}
