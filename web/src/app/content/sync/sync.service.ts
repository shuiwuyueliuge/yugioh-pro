import { Injectable } from '@angular/core';
import { Package } from './package';
import { HttpClient, HttpErrorResponse  } from '@angular/common/http';
import { PACKAGE } from '../../url';
import { throwError } from 'rxjs';
import { retry, catchError,timeout } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SyncService {

  constructor(private http: HttpClient) { }

  public async getCarsMedium2(): Promise<Package[]> {
    let uri = PACKAGE.uri;
    const res = await this.http.get<any>(uri).pipe(timeout(1111),catchError(this.handleError)).toPromise();
    return (<Package[]> res.data);
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
