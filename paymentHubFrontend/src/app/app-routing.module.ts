import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubcriptionComponent } from './subcription/subcription.component';

const routes: Routes = [
  {path: 'subscription-form', component: SubcriptionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
