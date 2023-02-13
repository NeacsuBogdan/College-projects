import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup ;
  DisplayError :boolean;

  constructor(private formBuilder : FormBuilder, private authService:AuthService,private router:Router) {
    this.DisplayError = false;
  this.loginForm = new FormGroup({});
  }

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm():void
  {
this.loginForm= this.formBuilder.group
({
  email:[null,[Validators.required,Validators.email]],
  password:[null,[Validators.required,Validators.minLength(8)]],
  
})
  }

  login(): void {
    if(this.loginForm.invalid)
    {
      return;
    }

    const payLoad = {
      email:this.email.value,password:this.password.value,
    }

    this.authService.login(payLoad).subscribe({
      next : (response) => {
        window.localStorage["token"] = response.token;
        this.router.navigate(['recipes/list']);
       },
       error : () => {
        this.DisplayError = true;
       }
    })
  }

  get email() : FormControl{
    return this.loginForm.get("email") as FormControl;
  }
  
  get password() : FormControl{
    return this.loginForm.get("password") as FormControl;
  }

}
