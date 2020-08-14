import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SyncPackageComponent } from './sync/package/package.component';
import { SyncLimitComponent } from './sync/limit/limit.component';
import { CardComponent } from './search/card/card.component';

const routes: Routes = [
  { path: 'sync/package', component: SyncPackageComponent },
  { path: 'sync/limit', component: SyncLimitComponent },
  { path: 'search/card', component: CardComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentRoutingModule { }
