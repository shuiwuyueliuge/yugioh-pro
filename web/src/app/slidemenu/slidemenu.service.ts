import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MenuItem } from 'primeng/api';
import { SLIDE_MENU } from '../url';

@Injectable({
  providedIn: 'root'
})
export class SlidemenuService {

  constructor(private http: HttpClient) { }

  public getSlidemenu(): Promise<MenuItem[]> {
    let uri = SLIDE_MENU.uri;
    return this.http.get<any>(uri)
      .toPromise()
      .then(res => <MenuItem[]>res.data)
      .then(data => data);
  }
}
