import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {


  validateForm: FormGroup;
  DisplayError :boolean;

  constructor (private formBuilder : FormBuilder, private authService:AuthService,private router:Router) {
    this.validateForm = new FormGroup({});
    this.DisplayError=false;
   }

  ngOnInit(): void {
    this.validateForm = this.formBuilder.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required,Validators.minLength(8)]],
      phone:[null,[Validators.required,this.validatePhoneNumber]],
      birthday: [null, [Validators.required, this.validateAge]]
    });
  }

  validatePhoneNumber(control: FormControl): {[key: string]: any} | null {
    const validPhoneNumber = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/.test(control.value);
    return validPhoneNumber ? null : { 'invalidPhoneNumber': true };
  }

  validateAge(control: FormControl) {
    const age = new Date(control.value).getFullYear();
    const currentYear = new Date().getFullYear();
    const minAge = 18;
    if (currentYear - age < minAge) {
      return { age: { value: control.value } };
    }
    return null;
  }

  submitForm(): void {
    if(this.validateForm.invalid) {
      this.DisplayError=true;
      return;
    }

    const payLoad = {
      email:this.email.value,password:this.password.value,
    }

    this.authService.register(payLoad).subscribe({
      next : (response) => {
        window.localStorage["token"] = response.token;
        
      },
      error: (error) => {
        this.router.navigate(["/auth/login"]);
        console.log(error);
      }
    });
  }
  get email() : FormControl{
    return this.validateForm.get("email") as FormControl;
  }
  
  get password() : FormControl{
    return this.validateForm.get("password") as FormControl;
  }

  cancel() {
    this.router.navigate(['auth/login']);
  }

}
