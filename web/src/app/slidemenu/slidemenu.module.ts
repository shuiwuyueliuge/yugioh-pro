import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SlidemenuComponent } from './slidemenu.component';
import { PanelMenuModule } from 'primeng/panelmenu';
import { SlidemenuService } from './slidemenu.service';

@NgModule({
  declarations: [
    SlidemenuComponent
  ],
  exports: [
    SlidemenuComponent
  ],
  imports: [
    CommonModule,
    PanelMenuModule
  ],
  providers: [
    SlidemenuService
  ]
})
export class SlidemenuModule { }
