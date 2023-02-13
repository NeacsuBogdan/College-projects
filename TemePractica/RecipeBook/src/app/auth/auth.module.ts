import { RecipeRoutingModule } from './../recipe/recipe-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule,FormBuilder } from '@angular/forms';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { HttpClientModule } from '@angular/common/http';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzSelectModule } from 'ng-zorro-antd/select';

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    RecipeRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    NzFormModule,
    NzInputModule,
    NzButtonModule,
    NzSelectModule,
    AuthRoutingModule
  ],
})
export class AuthModule { }
