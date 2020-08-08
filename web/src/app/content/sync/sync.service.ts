import { Injectable } from '@angular/core';
import { CardSource } from './card-source';
import { ResultData } from '../../common/http/response';
import { environment } from '../../../environments/environment';
import { HttpService } from '../../common/http/http.service';

@Injectable({
  providedIn: 'root'
})
export class SyncService {

  constructor(private httpService: HttpService) { }

  public getCardSources(): Promise<ResultData<CardSource[]>> {
    let uri = environment.cardSource;
    return this.httpService.get<ResultData<CardSource[]>>(uri, 3000);
  }
}
