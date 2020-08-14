import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentComponent } from './content.component';
import { ContentRoutingModule } from './content-routing.module';
import { SyncPackageComponent } from './sync/package/package.component';
import { PackageService } from './sync/package/package.service'; 
import { TableModule } from 'primeng/table';
import { SelectButtonModule } from 'primeng/selectbutton';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { ProgressBarModule } from 'primeng/progressbar';
import { StepsModule } from 'primeng/steps';
import { SyncLimitComponent } from './sync/limit/limit.component';
import { SyncService } from './sync/sync.service';
import { CardComponent } from './search/card/card.component';
import { CardService } from './search/card/card.service';

@NgModule({
  declarations: [
    ContentComponent,
    SyncPackageComponent,
    SyncLimitComponent,
    CardComponent
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
    ButtonModule,
    ProgressBarModule,
    StepsModule
  ],
  providers: [
    PackageService,
    SyncService,
    CardService
  ]
})
export class ContentModule { }
