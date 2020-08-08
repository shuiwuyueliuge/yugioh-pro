import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MenuItem } from 'primeng/api';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SlidemenuService {

  constructor(private http: HttpClient) { }

  public getSlidemenu(): Promise<MenuItem[]> {
    return this.http.get<any>(environment.slideMenu)
      .toPromise()
      .then(res => <MenuItem[]>res.data)
      .then(data => data);
  }
}
