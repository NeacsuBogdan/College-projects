import { Recipe } from '../../recipe.model';
import { Component, OnInit } from '@angular/core';
import { NzModalRef} from 'ng-zorro-antd/modal';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-recipe-modal',
  template: `
     <form nz-form [formGroup]="form">
  <nz-form-item>
    <nz-form-label nzFor="authorName">Nume autor</nz-form-label>
    <nz-form-control>
      <input nz-input 
        id="authorName" 
        formControlName="authorName" 
        name="authorName" 
        [ngClass]="{ 'is-invalid': (form.get('authorName')?.invalid && form.get('authorName')?.touched) }">
    </nz-form-control>
  </nz-form-item>
  <p *ngIf="form.get('authorName')?.invalid && form.get('authorName')?.touched" style="color: red;">
    Autorul este obligatoriu.
  </p>
  <nz-form-item>
    <nz-form-label nzFor="recipeName">Nume reteta</nz-form-label>
    <nz-form-control>
      <input nz-input 
        id="recipeName" 
        formControlName="recipeName" 
        name="recipeName"
        [ngClass]="{ 'is-invalid': (form.get('recipeName')?.invalid && form.get('recipeName')?.touched) }">
    </nz-form-control>
  </nz-form-item>
  <p *ngIf="form.get('recipeName')?.invalid && form.get('recipeName')?.touched" style="color: red;">
    Numele retetei este obligatoriu.
  </p>
  <nz-form-item>
    <nz-form-label nzFor="ingredients">Ingrediente</nz-form-label>
    <nz-form-control>
      <ul>
        <li *ngFor="let ingredient of recipe.ingredients; let i = index">
          {{ingredient}}
          <button (click)="removeIngredient(i)">
            X
          </button>
          
        </li>
          <input nz-input 
            #ingredientInput
            formControlName="ingredients"
            placeholder="Adauga ingredient"
            [ngClass]="{ 'is-invalid': (form.get('ingredients')?.invalid && form.get('ingredients')?.touched) }">
            
            <button [disabled]="form.controls['ingredients'].invalid" (click)="addIngredient(ingredientInput.value)">Adauga</button>
      </ul>
    </nz-form-control>
  </nz-form-item>
  <p *ngIf="recipe.ingredients.length == 0" style="color: red;">
    Va rugam adaugati cel putin un ingredient.
  </p>
  <nz-form-item >
    <nz-form-label nzFor="preparationMethod">Mod de preparare</nz-form-label>
    <nz-form-control>
      <textarea nz-input 
        id="preparationMethod" 
        formControlName="preparationMethod" 
        name="preparationMethod"
        cols="50"
        [ngClass]="{ 'is-invalid': (form.get('preparationMethod')?.invalid && form.get('preparationMethod')?.touched) }"></textarea>
    </nz-form-control>
  </nz-form-item>
  <p *ngIf="form.get('preparationMethod')?.invalid && form.get('preparationMethod')?.touched" style="color: red;">
    Modul de preparare este obligatoriu.
  </p>
  <nz-form-item>
    <nz-form-label nzFor="preparationTime">Timp de preparare in minute</nz-form-label>
    <nz-form-control>
      <input nz-input 
        id="preparationTime"
formControlName="preparationTime"
name="preparationTime"
[ngClass]="{ 'is-invalid': (form.get('preparationTime')?.invalid && form.get('preparationTime')?.touched) }">
</nz-form-control>
</nz-form-item>
<p *ngIf="form.get('preparationTime')?.invalid && form.get('preparationTime')?.touched" style="color: red;">
    Timpul de preparare este obligatoriu.
  </p>
<nz-form-item>
<nz-form-control>
<button nz-button (click)="saveRecipe()">Salveaza reteta</button>
</nz-form-control>
</nz-form-item>
<p *ngIf="showError" style="color: red;">
  Formularul este invalid!
</p>

  `,
   styleUrls: ['./addrecipemodal.component.scss'],
})
export class AddRecipeModalComponent implements OnInit {
recipe :Recipe = {
author: '',
name: '',
ingredients: [],
instructions: '',
time:0
};
newIngredient = '';
hasIngredients = false;
showError = false;

form: FormGroup;

constructor(private fb: FormBuilder, private modalRef: NzModalRef) {
 this.form = new FormGroup({});
}

ngOnInit(): void {
  this.form = this.fb.group({
    authorName: [this.recipe.author, [Validators.required,Validators.minLength(3)]],
    recipeName: [this.recipe.name, [Validators.required,Validators.minLength(3)]],
    ingredients:["",[Validators.minLength(3)]],
    preparationMethod: [this.recipe.instructions, [Validators.required,Validators.minLength(10)]],
    preparationTime: [this.recipe.time, [Validators.required,this.preparationTimeValidator,Validators.pattern("^[0-9]*$")]]
  });
}

preparationTimeValidator(control: AbstractControl) {
  if (control.value === 0) {
    return { invalidPreparationTime: true };
  }
  return null;
}


addIngredient(ingredient: string) {
  this.recipe.ingredients.push(ingredient);
  this.newIngredient = '';
  this.form.get('ingredients')?.setValue('');
  this.hasIngredients = true;
}

removeIngredient(index: number) {
this.recipe.ingredients.splice(index, 1);
if (this.recipe.ingredients.length === 0) {
  this.hasIngredients = false;
}
}

saveRecipe(): void {
  if (this.form.valid && this.hasIngredients!=false) {
  this.recipe.author = this.form.get('authorName')?.value;
  this.recipe.name = this.form.get('recipeName')?.value;
  this.recipe.ingredients = this.recipe.ingredients;
  this.recipe.instructions = this.form.get('preparationMethod')?.value;
  this.recipe.time = this.form.get('preparationTime')?.value;
    this.modalRef.close(this.recipe);
  } else {
    this.showError = true;
    console.error('Form is invalid');
  }
}

}
