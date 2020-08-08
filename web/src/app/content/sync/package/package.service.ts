import { Injectable } from '@angular/core';
import { Package } from './package';
import { environment } from '../../../../environments/environment';
import { PackageData } from './package-data';
import { HttpService } from '../../../common/http/http.service';
import { ResultData } from '../../../common/http/response';

@Injectable({
  providedIn: 'root'
})
export class PackageService {

  constructor(private httpService: HttpService) { }

  public getPackage(cardSource: number): Promise<ResultData<Package[]>> {
    let uri = environment.package;
    return this.httpService.get<ResultData<Package[]>>(`${uri}${cardSource}`, 3000);
  }

  public publishPackage(data: PackageData, cardSource: number): Promise<ResultData<string[]>> {
    let uri = environment.package;
    return this.httpService.postJson<ResultData<string[]>>(`${uri}${cardSource}`, data, 3000);
  }
}
