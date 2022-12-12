import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  open()
  {
    window.open("https://github.com/cstAcademy/UTC2022")
  }

}
