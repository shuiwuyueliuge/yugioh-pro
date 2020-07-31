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

  public channelId: string;

  aaa:boolean = true;

  items: MenuItem[];

  activeIndex: number;

  constructor(private syncService: SyncService, private webSocketService: WebSocketService) {
    this.syncService.getCardSources().then(source => {
      source.forEach(data => {
        this.cardSources.push({ label: data.name, value: { id: data.type } });
      });

      this.selectedCardSource = this.cardSources[0].value;
      this.initPackageData(this.cardSources[0].value.id);
    });

    this.webSocketService.getWsObservable('selectPackage').subscribe(res => {
     
      if (this.aaa) {
        this.channelId = res.channelId;
        this.aaa = false;
      } else {
        console.log(res);
        this.selectedPackages.forEach(data => {
          if (data.uri == res.data.packageName) {
            if (data.progress == 100) {
              return;
            }

            data.progress = res.data.progress;
          }
        });
      }
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
      this.selectedPackages.push(selPack);
      let publishData = new Array<string>();
      this.selectedPackages.forEach(data => {
        publishData.push(data.uri);
        data.progressShow = true;
      });
  
      this.syncService.publishPackage({ packageUris: publishData, priority: 2, channelId: this.channelId }, this.selectedCardSource.id);
  }

  public selCardSource(): void {
    this.initPackageData(this.selectedCardSource.id);
  }

  private initPackageData(cardSource: number): void {
    this.syncService.getPackage(cardSource).then(packages => {
      packages.forEach(pack => {
        pack.seq = 1;
        pack.progressShow = false;
      });

      this.packages = packages;
    });
  }
}
