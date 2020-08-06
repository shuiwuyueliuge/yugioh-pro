import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { throwError, EMPTY } from 'rxjs';
import { retry, catchError, timeout, map } from 'rxjs/operators';
import { ResultData } from './response';
import { ToastService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient, private toastService: ToastService) { }

  public async get<T>(url: string, time: number): Promise<T> {
    try {
      let res = await this.http.get<T>(url).pipe(map(res => res as ResultData<T>), timeout(time), catchError(<any>this.handleError)).toPromise();
      if ((<ResultData<T>>res).code != 200) {
        this.toastService.addMsg('error', '服务器异常', `${(<ResultData<T>>res).msg}`);
        res = null;
      }

      return res;
    } catch (error) {
      this.toastService.addMsg('error', '服务器异常', `${error}`);
      return null;
    }
  }

  public async postJson<T>(url: string, data: any, time: number): Promise<T> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    try {
      let res =  await this.http.post<T>(url, JSON.stringify(data), httpOptions).pipe(map(res => res as ResultData<T>), timeout(30000), catchError(<any>this.handleError)).toPromise();
      if ((<ResultData<T>>res).code != 200) {
        this.toastService.addMsg('error', '服务器异常', `${(<ResultData<T>>res).msg}`);
        res = null;
      }

      return res;
    } catch (error) {
      this.toastService.addMsg('error', '服务器异常', `${error}`);
      return null;
    }
  }

  private handleError(error: HttpErrorResponse, caught: any): any {
    return throwError(`${error.url} ${error.status} ${error.statusText}`);
  }
}
