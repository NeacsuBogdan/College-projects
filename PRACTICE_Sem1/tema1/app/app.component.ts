import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'lab2';
  content = ["a","b","c"];
  displayHeader: boolean = false;

  changeHeader(){
    this.displayHeader = !this.displayHeader;
  }
}
