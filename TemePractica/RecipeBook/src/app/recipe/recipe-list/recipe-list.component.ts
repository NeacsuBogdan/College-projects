import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe.model';
import { NzModalService } from 'ng-zorro-antd/modal';
import { AddRecipeModalComponent } from './addrecipemodal/addrecipemodal.component';
import { NzModalRef } from 'ng-zorro-antd/modal'
import { RecipeService } from './recipe.service';


@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.scss']
})
export class RecipeListComponent implements OnInit {

  modalRef?: NzModalRef;
  searchTerm : string= '';
  selectedOption : string="";
  nonFilteredRecipes: Recipe[]=[];
  recipes: Recipe[] = [];

  constructor(private modalService: NzModalService,private recipeService: RecipeService) {}

  searchRecipes() {
    this.recipeService.searchRecipes(this.searchTerm).subscribe(recipes => this.recipes = recipes)
  }

  sortRecipes() {
  this.recipeService.sortRecipes(this.selectedOption).subscribe(recipes => this.recipes = recipes)
    }


  openModal() {
    this.modalRef = this.modalService.create({
      nzContent: AddRecipeModalComponent,
      nzFooter:null,
      nzMaskClosable:false
    });

    this.modalRef.afterClose.subscribe((recipe: Recipe) => {
      if (recipe) {
        this.recipeService.addRecipe(recipe)
        this.recipeService.getRecipes().subscribe(recipes => this.recipes = recipes)
      }
    });
  }
  
  removeRecipe(recipe:Recipe) {
    const index = this.recipes.indexOf(recipe);
    this.recipeService.removeRecipe(index,this.searchTerm,this.selectedOption);
    this.recipeService.getRecipes().subscribe(recipes => this.recipes = recipes)
  }

  ngOnInit() {
    this.recipeService.getRecipes().subscribe(recipes => this.recipes = recipes);
}

  

}