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
        this.cardSources.push({ label: data.name, value: { id: data.id } });
      });

      this.selectedCardSource = this.cardSources[0].value;
      this.syncService.getPackage(this.cardSources[0].value.id).then(packages => {
        let seq = 1;
        packages.forEach(pack => {
          pack.seq = seq;
          seq++;
        });

        this.packages = packages;
      });
    });
  }

  ngOnInit() { }

  public selectPackage(): void {
    console.log(this.selectedPackages);
  }

  public selCardSource(): void {
    console.log(this.selectedCardSource.id);
  }
}
