import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  public menuActive: boolean;

  public items: MenuItem[];

  public smallTab: boolean;

  constructor(private router: Router) {
    this.menuActive = false;
    this.smallTab = false;
    this.items = [
      { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: '/' }
    ];
  }

  ngOnInit() {
  }

  public changeMenuActive(event: any): void {
    this.menuActive = event;
  }

  public closeItem(event: any, index: number): void {
    let top = this.items[index - 1];
    this.items = this.items.filter((item, i) => i !== index);
    if (this.items.length <= 9) {
      this.smallTab = false;
    }

    this.router.navigate([top.routerLink]);
    if (event != null) {
      event.preventDefault();
    }
  }

  public addTab(event: any): void {
    for (let i = 0; i < this.items.length; i++) {
      if (this.items[i].label == event.label) {
        return;
      }
    }

    if (this.items.length >= 9) {
      this.smallTab = true;
    }

    if (this.items.length == 14) {
      this.closeItem(null, 1);
    }

    this.items.push({ label: event.label, icon: event.icon, routerLink: event.routerLink });
  }
}
