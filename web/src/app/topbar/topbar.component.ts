import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'ygo-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit {

  @Output()
  private menuActive = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  public onMenuButtonClick(active: boolean, event: any): void {
    this.menuActive.emit(active);
    event.preventDefault();
  }
}
