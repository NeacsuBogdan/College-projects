import { LoginComponent } from './login/login.component';
import { ListComponent } from './list/list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:'list',component:ListComponent},
  {path: 'login',component:LoginComponent, loadChildren: () => import('./admin.module').then(m => m.AdminModule)}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
