import { Injectable } from '@angular/core';
import { HttpService } from '../../../common/http/http.service';
import { ResultData } from '../../../common/http/response';
import { Limit } from './limit';
import { LimitData } from './limit-data';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LimitService {

  constructor(private httpService: HttpService) { }

  public getLimit(cardSource: number): Promise<ResultData<Limit[]>> {
    let uri = environment.limit;
    return this.httpService.get<ResultData<Limit[]>>(`${uri}${cardSource}`, 3000);
  }

  public publishLimit(data: LimitData, cardSource: number): Promise<ResultData<string[]>> {
    let uri = environment.limit;
    return this.httpService.postJson<ResultData<string[]>>(`${uri}${cardSource}`, data, 3000);
  }
}
