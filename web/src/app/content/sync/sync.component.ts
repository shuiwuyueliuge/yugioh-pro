import { Component, OnInit } from '@angular/core';
import { Package } from './package';
import { SyncService } from './sync.service';
import { SelectItem } from 'primeng/api';
import { WebSocketService } from '../web-socket.service';
import { Subscription } from "rxjs/internal/Subscription";
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-sync',
  templateUrl: './sync.component.html',
  styleUrls: ['./sync.component.scss']
})
export class SyncComponent implements OnInit {

  public packages: Package[] = [];

  public selectedPackages: Package[] = [];

  public cardSources: SelectItem[] = [];

  public selectedCardSource: any;

  a: Subscription;

  items: MenuItem[];

  activeIndex: number;

  constructor(private syncService: SyncService, private webSocketService: WebSocketService) {
    this.a = this.webSocketService.getWsObservable('sync').subscribe(data => console.log(data));
    this.syncService.getCardSources().then(source => {
      source.forEach(data => {
        this.cardSources.push({ label: data.name, value: { id: data.type } });
      });

      this.selectedCardSource = this.cardSources[0].value;
      this.initPackageData(this.cardSources[0].value.id);
    });
  }

  ngOnInit() {
    this.items = [
      {label: 'Step 1',command: (event: any) => {
        this.syncService.test();
    }},
      {label: 'Step 2'},
      {label: 'Step 3'}
  ];

    // let interval = setInterval(() => {
    //   this.value = this.value + Math.floor(Math.random() * 10) + 3;
    //   if (this.value >= 100) {
    //     this.value = 100;
    //     //this.messageService.add({severity: 'info', summary: 'Success', detail: 'Process Completed'});
    //     clearInterval(interval);
    //   }
    // }, 1000);
  }

  public selectPackage(selPack: Package): void {
    let uri = selPack.uri;
    selPack.progressShow = true;
    let publishData = new Array<string>();
    publishData.push(uri);
    this.selectedPackages.forEach(data => {
      publishData.push(data.uri);
      data.progressShow = true;
    });

    this.syncService.publishPackage({ packageUris: publishData, priority: 2 }, this.selectedCardSource.id);
  }

  public selCardSource(): void {
    this.initPackageData(this.selectedCardSource.id);
  }

  private initPackageData(cardSource: number): void {
    this.syncService.getPackage(cardSource).then(packages => {
      let seq = 1;
      packages.forEach(pack => {
        pack.seq = seq;
        pack.progress = (seq > 100) ? 100 : seq;
        pack.progressShow = false;
        seq++;
      });

      this.packages = packages;
    });
  }
}
