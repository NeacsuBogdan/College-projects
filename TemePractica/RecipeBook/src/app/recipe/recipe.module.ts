import { HomeComponent } from './../home/home.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecipeRoutingModule } from './recipe-routing.module';
import { RecipeListComponent } from './recipe-list/recipe-list.component';
import { RecipeCardComponent } from './recipe-card/recipe-card.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NzModalModule, NzModalService, NzModalRef } from 'ng-zorro-antd/modal';
import { RecipecardcontentComponent } from './recipe-card/recipecardcontent/recipecardcontent.component';
import { AddRecipeModalComponent } from './recipe-list/addrecipemodal/addrecipemodal.component';
import { NzFormModule } from 'ng-zorro-antd/form';


@NgModule({
  declarations: [
    RecipeListComponent,
    RecipeCardComponent,
    RecipecardcontentComponent,
    AddRecipeModalComponent,
    HomeComponent
    
  ],
  imports: [
    CommonModule,
    RecipeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NzModalModule,
    NzFormModule,


   ]
})
export class RecipeModule { }
