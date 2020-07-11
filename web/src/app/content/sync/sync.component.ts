import { Component, OnInit } from '@angular/core';
import { Car } from './car';
import { SyncService } from './sync.service';
@Component({
  selector: 'app-sync',
  templateUrl: './sync.component.html',
  styleUrls: ['./sync.component.scss']
})
export class SyncComponent implements OnInit {

  cars: Car[] = [];

  cols: any[];

  first = 0;

  rows = 10;

  constructor(private syncService: SyncService) {
    this.syncService.getCarsMedium().then(cars => {
      let seq = 1;
      cars.forEach(car => {
          car.seq = seq;
          seq++;
      });

      this.cars = cars;
    });

    this.cols = [
      { field: 'seq', header: 'Seq' },
      { field: 'vin', header: 'Vin' },
      { field: 'year', header: 'Year' },
      { field: 'brand', header: 'Brand' },
      { field: 'color', header: 'Color' }
  ];
  }

  ngOnInit() {}
}
