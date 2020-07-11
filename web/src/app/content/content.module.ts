import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentComponent } from './content.component';
import { ContentRoutingModule } from './content-routing.module';
import { SyncComponent } from './sync/sync.component';
import { SyncService } from './sync/sync.service'; 
import { TableModule } from 'primeng/table';
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
    ButtonModule
  ],
  providers: [
    SyncService
  ]
})
export class ContentModule { }
