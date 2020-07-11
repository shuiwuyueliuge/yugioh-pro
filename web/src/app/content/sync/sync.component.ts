import { Component, OnInit } from '@angular/core';
import { Package } from './package';
import { SyncService } from './sync.service';

@Component({
  selector: 'app-sync',
  templateUrl: './sync.component.html',
  styleUrls: ['./sync.component.scss']
})
export class SyncComponent implements OnInit {

  cars: Package[] = [];

  selectedCustomers: Package[];

  cols: any[];

  constructor(private syncService: SyncService) {
    this.syncService.getCarsMedium2().then(cars => {
      let seq = 1;
      cars.forEach(car => {
          car.seq = seq;
          seq++;
      });

      this.cars = cars;
    });

    this.cols = [
      { field: 'seq', header: '' },
      { field: 'name', header: 'Name' },
      { field: 'uri', header: 'Uri' }
  ];
  }

  ngOnInit() {}

  selectRow() {
    console.log(this.selectedCustomers);
}
}
