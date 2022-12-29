import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { ListComponent } from './list/list.component';
import { LoginComponent } from './login/login.component';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { CardComponent } from './card/card.component';


@NgModule({
  declarations: [
    ListComponent,
    LoginComponent,
    CardComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    NzButtonModule,
    NzModalModule,
    NzCardModule
  ],
})
export class AdminModule { }
