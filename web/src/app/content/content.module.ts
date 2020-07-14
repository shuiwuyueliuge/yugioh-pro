import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentComponent } from './content.component';
import { ContentRoutingModule } from './content-routing.module';
import { SyncComponent } from './sync/sync.component';
import { SyncService } from './sync/sync.service'; 
import { TableModule } from 'primeng/table';
import { SelectButtonModule } from 'primeng/selectbutton';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [
    ContentComponent,
    SyncComponent
  ],
  exports: [
    ContentComponent
  ],
  imports: [
    CommonModule,
    ContentRoutingModule,
    TableModule,
    SelectButtonModule,
    FormsModule,
    ButtonModule
  ],
  providers: [
    SyncService
  ]
})
export class ContentModule { }
