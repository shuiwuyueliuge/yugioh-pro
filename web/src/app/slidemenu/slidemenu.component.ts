import { Component, Output, EventEmitter } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { SlidemenuService } from './slidemenu.service';

@Component({
  selector: 'ygo-slidemenu',
  templateUrl: './slidemenu.component.html',
  styleUrls: ['./slidemenu.component.scss']
})
export class SlidemenuComponent {

  public items: MenuItem[];

  @Output()
  private item = new EventEmitter();

  constructor(private carService: SlidemenuService) {
    this.carService.getSlidemenu().then(menus => {
      this.initCommand(menus);
      let currentUri = location.pathname;
      if(currentUri != '/') {
        let res = this.findRouterLink(menus, currentUri);
        this.item.emit(res);
      }

      this.items = menus;
    });

    /*
        this.items = [
          {
            label: 'File',
            icon: 'pi pi-pw pi-file',
            items: [{
              label: 'New',
              icon: 'pi pi-fw pi-plus',
              items: [
                { label: 'User', icon: 'pi pi-fw pi-user-plus', items: [
                  { label: 'Filter', icon: 'pi pi-fw pi-filter'}
                ] },
                { label: 'Filter', icon: 'pi pi-fw pi-filter' }
              ]
            },
            {
              label: 'Open', icon: 'pi pi-fw pi-external-link', routerLink: '/', command: (event) => this.item.emit(event)
            },
            {
              label: 'Quit', icon: 'pi pi-fw pi-times', routerLink: '/content/sync', command: (event) => this.item.emit(event)
            }
            ]
          }//,
          // {
          //   label: 'Edit',
          //   icon: 'pi pi-fw pi-pencil',
          //   items: [
          //     { label: 'Delete', icon: 'pi pi-fw pi-trash' },
          //     { label: 'Refresh', icon: 'pi pi-fw pi-refresh' }
          //   ]
          // },
          // {
          //   label: 'Help',
          //   icon: 'pi pi-fw pi-question',
          //   items: [
          //     {
          //       label: 'Contents',
          //       icon: 'pi pi-pi pi-bars'
          //     },
          //     {
          //       label: 'Search',
          //       icon: 'pi pi-pi pi-search',
          //       items: [
          //         {
          //           label: 'Text',
          //           items: [
          //             {
          //               label: 'Workspace'
          //             }
          //           ]
          //         },
          //         {
          //           label: 'User',
          //           icon: 'pi pi-fw pi-file',
          //         }
          //       ]
          //     }
          //   ]
          // },
          // {
          //   label: 'Actions',
          //   icon: 'pi pi-fw pi-cog',
          //   items: [
          //     {
          //       label: 'Edit',
          //       icon: 'pi pi-fw pi-pencil',
          //       items: [
          //         { label: 'Save', icon: 'pi pi-fw pi-save' },
          //         { label: 'Update', icon: 'pi pi-fw pi-save' },
          //       ]
          //     },
          //     {
          //       label: 'Other',
          //       icon: 'pi pi-fw pi-tags',
          //       items: [
          //         { label: 'Delete', icon: 'pi pi-fw pi-minus' }
          //       ]
          //     }
          //   ]
          // },
          // {
          //   label: 'Actions',
          //   icon: 'pi pi-fw pi-cog',
          //   items: [
          //     {
          //       label: 'Edit',
          //       icon: 'pi pi-fw pi-pencil',
          //       items: [
          //         { label: 'Save', icon: 'pi pi-fw pi-save' },
          //         { label: 'Update', icon: 'pi pi-fw pi-save' },
          //       ]
          //     },
          //     {
          //       label: 'Other',
          //       icon: 'pi pi-fw pi-tags',
          //       items: [
          //         { label: 'Delete', icon: 'pi pi-fw pi-minus' }
          //       ]
          //     }
          //   ]
          // },
          // {
          //   label: 'Actions',
          //   icon: 'pi pi-fw pi-cog',
          //   items: [
          //     {
          //       label: 'Edit',
          //       icon: 'pi pi-fw pi-pencil',
          //       items: [
          //         { label: 'Save', icon: 'pi pi-fw pi-save' },
          //         { label: 'Update', icon: 'pi pi-fw pi-save' },
          //       ]
          //     },
          //     {
          //       label: 'Other',
          //       icon: 'pi pi-fw pi-tags',
          //       items: [
          //         { label: 'Delete', icon: 'pi pi-fw pi-minus' }
          //       ]
          //     }
          //   ]
          // },
          // {
          //   label: 'Actions',
          //   icon: 'pi pi-fw pi-cog',
          //   items: [
          //     {
          //       label: 'Edit',
          //       icon: 'pi pi-fw pi-pencil',
          //       items: [
          //         { label: 'Save', icon: 'pi pi-fw pi-save' },
          //         { label: 'Update', icon: 'pi pi-fw pi-save' },
          //       ]
          //     },
          //     {
          //       label: 'Other',
          //       icon: 'pi pi-fw pi-tags',
          //       items: [
          //         { label: 'Delete', icon: 'pi pi-fw pi-minus' }
          //       ]
          //     }
          //   ]
          // }
        ];
        */
  }

  private initCommand(items: MenuItem[]): void {
    items.forEach(item => {
      if (item.items != null && item.items.length != 0) {
        this.initCommand(item.items);
      }

      if (item.routerLink != null) {
        item.command = (event) => this.item.emit(event.item);
      }
    });
  }

  private findRouterLink(items: MenuItem[], uri: string): MenuItem {
    for(let i = 0; i < items.length; i++) {
      let item = items[i];
      if (item.items != null && item.items.length != 0) {
        let res = this.findRouterLink(item.items, uri);
        if (res != null) {
          return res;
        }
      }

      if (item.routerLink == uri) {
        return item;
      }
    }

    return null;
  }
}
