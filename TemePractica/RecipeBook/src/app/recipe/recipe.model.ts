export class Recipe {
  author: string;
  name: string;
  ingredients: string[];
  instructions: string;
  time: number;

  constructor(author: string, name: string, ingredients: string[], instructions: string, time: number) {
    this.author = author;
    this.name = name;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.time = time;
  }
}