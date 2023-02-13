import { Recipe } from '../recipe.model';
import { Component, OnInit, Input } from '@angular/core';
import { NzModalService,NzModalRef } from 'ng-zorro-antd/modal';
import { RecipecardcontentComponent } from './recipecardcontent/recipecardcontent.component';
import { Output,EventEmitter } from '@angular/core';
import './card-animation.js';
@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.scss']
})
export class RecipeCardComponent implements OnInit {
  @Input() recipe:Recipe;
  constructor(private modalService: NzModalService) {
    this.recipe = {
      name: '',
      author: '',
      ingredients: [],
      instructions: '',
      time: 0
    };

  }

  ngOnInit(): void {}

  @Output() delete = new EventEmitter<any>();

  deleteRecipe(recipe:Recipe) {
    this.delete.emit(recipe);
  }

  showInstructions(): void {
    const modal:NzModalRef = this.modalService.create({
        nzTitle: 'Instructions for ' + this.recipe.name,
        nzComponentParams: { recipe: this.recipe },
        nzContent:RecipecardcontentComponent,
    });
  }
}