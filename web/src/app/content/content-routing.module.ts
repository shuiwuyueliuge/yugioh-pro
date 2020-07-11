import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SyncComponent } from './sync/sync.component';

const routes: Routes = [{ path: 'sync', component: SyncComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentRoutingModule { }
