import { Injectable } from '@angular/core';
import { Package } from './package';
import { CardSource } from './card-source';
import { HttpClient } from '@angular/common/http';
import { PACKAGE, CARD_SOURCE, PACKAGE_PUBLISH } from '../../url';
import { PackageData } from './package-data';
import { HttpService } from '../../http.service';
import { ResultData } from '../../response';

@Injectable({
  providedIn: 'root'
})
export class SyncService {

  constructor(private http: HttpClient, private httpService: HttpService) { }

  public getPackage(cardSource: number): Promise<ResultData<Package[]>> {
    let uri = PACKAGE.uri;
    return this.httpService.get<ResultData<Package[]>>(`${uri}${cardSource}`, 3000);
  }

  public getCardSources(): Promise<ResultData<CardSource[]>> {
    let uri = CARD_SOURCE.uri;
    return this.httpService.get<ResultData<CardSource[]>>('assets/showcase/data/cardsource.json', 3000);
  }

  public async publishPackage(data: PackageData, cardSource: number): Promise<ResultData<string[]>> {
    let uri = PACKAGE_PUBLISH.uri;
    return this.httpService.postJson<ResultData<string[]>>(`${uri}${cardSource}`, data, 3000);
  }

  public test(): void {
    console.log(12);
  }
}
