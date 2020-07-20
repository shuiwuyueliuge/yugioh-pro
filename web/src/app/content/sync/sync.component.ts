import { Component, OnInit } from '@angular/core';
import { Package } from './package';
import { SyncService } from './sync.service';
import { SelectItem } from 'primeng/api';

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

  constructor(private syncService: SyncService) {
    this.syncService.getCardSources().then(source => {
      source.forEach(data => {
        this.cardSources.push({ label: data.name, value: { id: data.type } });
      });

      this.selectedCardSource = this.cardSources[0].value;
      this.initPackageData(this.cardSources[0].value.id);
    });
  }

  ngOnInit() { }

  public selectPackage(uri: string): void {
    console.log(this.selectedPackages);
    console.log(uri);
    let publishData = new Array<string>(this.selectedPackages.length);
    publishData.push(uri);
    this.selectedPackages.forEach(data => publishData.push(data.uri));
    this.syncService.publishPackage({packageUris: publishData, priority: 2}, this.selectedCardSource.id);
  }

  public selCardSource(): void {
   this.initPackageData(this.selectedCardSource.id);
  }

  private initPackageData(cardSource: number): void {
    this.syncService.getPackage(cardSource).then(packages => {
      let seq = 1;
      packages.forEach(pack => {
        pack.seq = seq;
        seq++;
      });

      this.packages = packages;
    });
  }
}
