import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Recipe } from '../recipe.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  private recipesUrl = 'http://localhost:3000/getRecipes';
  private addRecipeUrl = 'http://localhost:3000/addRecipe';
  private removeRecipeUrl = "http://localhost:3000/removeRecipe";
  private searchRecipesUrl = 'http://localhost:3000/searchRecipes';
  private sortRecipesUrl = 'http://localhost:3000/sortRecipes';

  constructor(private http: HttpClient) { }

  getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipesUrl);
  }

  addRecipe(recipe: Recipe) {
    this.http.post<Recipe>(this.addRecipeUrl, recipe).subscribe(response => {
      console.log("Response received:", response);
    });
  }

  removeRecipe(index: number, searchTerm: string, selectedOption: string) {

    this.http.delete(this.removeRecipeUrl, {
      body: {
        index,
        searchTerm,
        selectedOption
      }
    }).subscribe(response => {
      console.log("Response received:", response);
    });
  }


  searchRecipes(searchTerm: string): Observable<Recipe[]> {
    const url = `${this.searchRecipesUrl}?searchTerm=${searchTerm}`;
    return this.http.get<Recipe[]>(url);
  }

  sortRecipes(sortOption: string): Observable<Recipe[]> {
    const url = `${this.sortRecipesUrl}?sortOption=${sortOption}`;
    return this.http.get<Recipe[]>(url);
  }
}