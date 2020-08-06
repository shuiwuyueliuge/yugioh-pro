import { Component, OnInit } from '@angular/core';
import { Package } from './package';
import { SyncService } from './sync.service';
import { SelectItem } from 'primeng/api';
import { WebSocketService } from '../web-socket.service';
import { WebSocketMsg } from '../web-socket-msg';
import { PackageAnalyseEvent } from '../package-analyse-event';

@Component({
  selector: 'app-sync',
  templateUrl: './sync.component.html',
  styleUrls: ['./sync.component.scss']
})
export class SyncComponent implements OnInit {

  public packages: Package[] = [];

  public selectedPackages: Package[] = [];

  public waitPackages: Map<string, Package> = new Map();

  public cardSources: SelectItem[] = [];

  public selectedCardSource: any;

  public channelId: string;

  constructor(private syncService: SyncService, private webSocketService: WebSocketService) {
    this.initDataSource();
    this.connectWebSocket();
  }

  ngOnInit() {
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
      data.buttonDisabled = true;
      data.buttonLabel = '解析中....';
    });

    let packageData = {
      packageUris: publishData,
      channelId: this.channelId,
      subscribe: 'selectPackage'
    };

    this.syncService.publishPackage(packageData, this.selectedCardSource.id);
    this.selectedPackages.forEach(data => this.waitPackages.set(data.uri, data));
    this.selectedPackages = [];
  }

  public selCardSource(): void {
    this.initPackageData(this.selectedCardSource.id);
  }

  private initDataSource(): void {
    this.syncService.getCardSources().then(source => {
      if (source == null) {
        return;
      }

      source.data.forEach(data => {
        this.cardSources.push({ label: data.name, value: { id: data.type } });
      });

      this.selectedCardSource = this.cardSources[0].value;
      this.initPackageData(this.cardSources[0].value.id);
    });
  }

  private initPackageData(cardSource: number): void {
    this.syncService.getPackage(cardSource).then(packages => {
      if (packages == null) {
        return;
      }

      packages.data.forEach(pack => {
        pack.seq = 1;
        pack.progressShow = false;
        pack.buttonDisabled = false;
        pack.buttonLabel = '开始解析';
      });

      this.packages = packages.data;
    });
  }

  private connectWebSocket(): void {
    this.webSocketService.getWsObservable('selectPackage').subscribe((res: WebSocketMsg<PackageAnalyseEvent>) => {
      this.channelId = res.channelId;
      if (res.data == null) {
        return;
      }

      let waitPackage = this.waitPackages.get(res.data.packageName);
      if (waitPackage == null) {
        return;
      }

      waitPackage.progress = res.data.progress;
      if (res.code != 200) {
        waitPackage.buttonLabel = '解析异常';
        this.waitPackages.delete(waitPackage.uri);
        return;
      }

      if (waitPackage.progress == 100) {
        waitPackage.buttonLabel = '解析完成';
        this.waitPackages.delete(waitPackage.uri);
      }
    });
  }
}