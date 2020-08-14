import { Injectable } from '@angular/core';
import { HttpService } from '../../../common/http/http.service';
import { ResultData } from '../../../common/http/response';
import { environment } from '../../../../environments/environment';
import { Card } from './card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private httpService: HttpService) { }

  public getCard(): Promise<ResultData<Card[]>> {
    let uri = environment.card;
    return this.httpService.get<ResultData<Card[]>>(`${uri}`, 3000);
  }
}
