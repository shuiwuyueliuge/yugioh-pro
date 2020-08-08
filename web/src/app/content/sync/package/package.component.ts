import { Component, OnInit } from '@angular/core';
import { Package } from './package';
import { PackageService } from './package.service';
import { SelectItem } from 'primeng/api';
import { WebSocketService } from '../../../common/web-socket/web-socket.service';
import { WebSocketMsg } from '../../../common/web-socket/web-socket-msg';
import { PackageAnalyseEvent } from '../../../common/web-socket/package-analyse-event';
import { SyncService } from '../sync.service';

@Component({
  selector: 'app-sync-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.scss']
})
export class SyncPackageComponent implements OnInit {

  public packages: Package[] = [];

  public selectedPackages: Package[] = [];

  public waitPackages: Map<string, Package> = new Map();

  public cardSources: SelectItem[] = [];

  public selectedCardSource: any;

  public channelId: string;

  constructor(private packageService: PackageService,
              private webSocketService: WebSocketService,
              private syncService: SyncService) {
    this.initDataSource();
    this.connectWebSocket();
  }

  ngOnInit() { }

  public selectPackage(selPack: Package): void {
    this.selectedPackages.push(selPack);
    let publishData = new Array<string>();
    this.selectedPackages.forEach(data => {
      publishData.push(data.uri);
      data.progressShow = true;
      data.buttonDisabled = true;
      data.buttonLabel = '解析中....';
      if(!data.progress) {
        data.progress = Math.floor(Math.random() * 30) + 8;
      }
    });

    let packageData = {
      urls: publishData,
      channelId: this.channelId,
      subscribe: 'selectPackage'
    };

    this.packageService.publishPackage(packageData, this.selectedCardSource.id);
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
    this.packageService.getPackage(cardSource).then(packages => {
      if (packages == null) {
        return;
      }

      packages.data.forEach(pack => {
        pack.progressShow = false;
        pack.buttonDisabled = false;
        pack.buttonLabel = '开始解析';
      });

      this.packages = packages.data;
    });
  }

  private connectWebSocket(): void {
    this.webSocketService.getWsObservable('selectPackage').subscribe((res: WebSocketMsg<PackageAnalyseEvent>) => {
      this.channelId = res.source.channelId;
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