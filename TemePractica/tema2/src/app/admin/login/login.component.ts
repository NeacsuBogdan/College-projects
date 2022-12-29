import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  modList = [{
    id: '1',
    title: 'First title',
    description: 'First description'
  },
  {
    id: '2',
    title: 'Second title',
    description: 'Second description'
  }
  ];

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

}
