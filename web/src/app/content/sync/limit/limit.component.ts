import { Component, OnInit } from '@angular/core';
import { Limit } from './limit';
import { SelectItem } from 'primeng/api';
import { SyncService } from '../sync.service';
import { LimitService } from './limit.service';
import { WebSocketService } from '../../../common/web-socket/web-socket.service';
import { WebSocketMsg } from '../../../common/web-socket/web-socket-msg';
import { LimitAnalyseEvent } from '../../../common/web-socket/Limit-analyse-event';

@Component({
  selector: 'app-limit',
  templateUrl: './limit.component.html',
  styleUrls: ['./limit.component.scss']
})
export class SyncLimitComponent implements OnInit {

  public cardSources: SelectItem[] = [];

  public selectedCardSource: any;

  public limits: Limit[] = [];

  public selectedLimits: Limit[] = [];

  public waitLimits: Map<string, Limit> = new Map();

  public channelId: string;

  constructor(private syncService: SyncService, 
              private webSocketService: WebSocketService, 
              private limitService: LimitService) {
    this.initDataSource();
    this.connectWebSocket();
   }

  ngOnInit(): void {
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
      this.initLimitData(this.cardSources[0].value.id);
    });
  }

  private initLimitData(cardSource: number): void {
    this.limitService.getLimit(cardSource).then(limit => {
      if (limit == null) {
        return;
      }

      limit.data.forEach(lim => {
        lim.progressShow = false;
        lim.buttonDisabled = false;
        lim.buttonLabel = '开始解析';
      });

      this.limits = limit.data;
    });
  }

  public selCardSource(): void {
    this.initLimitData(this.selectedCardSource.id);
  }

  public selectLimit(selPack: Limit): void {
    this.selectedLimits.push(selPack);
    let publishData = new Array<string>();
    this.selectedLimits.forEach(data => {
      publishData.push(data.url);
      data.progressShow = true;
      data.buttonDisabled = true;
      data.buttonLabel = '解析中....';
      if(!data.progress) {
        data.progress = Math.floor(Math.random() * 30) + 8;
      }
    });

    let limitData = {
      urls: publishData,
      channelId: this.channelId,
      subscribe: 'selectLimit'
    };

     this.limitService.publishLimit(limitData, this.selectedCardSource.id);
     this.selectedLimits.forEach(data => this.waitLimits.set(data.url, data));
     this.selectedLimits = [];
  }

  private connectWebSocket(): void {
    this.webSocketService.getWsObservable('selectLimit').subscribe((res: WebSocketMsg<LimitAnalyseEvent>) => {
      this.channelId = res.source.channelId;
      if (res.data == null) {
        return;
      }

      let waitLimit = this.waitLimits.get(res.data.limitName);
      if (waitLimit == null) {
        return;
      }

      waitLimit.progress = res.data.progress;
      if (res.code != 200) {
        waitLimit.buttonLabel = '解析异常';
        this.waitLimits.delete(waitLimit.url);
        return;
      }

      if (waitLimit.progress == 100) {
        waitLimit.buttonLabel = '解析完成';
        this.waitLimits.delete(waitLimit.url);
      }
    });
  }
}
