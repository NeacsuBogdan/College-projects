import { HomeComponent } from './register/home/home.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path : "",
    loadChildren:()=>import("./register/register.module").then((m)=>m.RegisterModule),
    pathMatch:'full',
  },
  {
  path:"home",
  component:HomeComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
