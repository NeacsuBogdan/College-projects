import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
 
  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {
      this.registerForm = this.formBuilder.group({
          name: ['', Validators.required],
          email: ['', [Validators.required, Validators.email]],
          password: ['',[Validators.required,Validators.minLength(8)]]
          repassword: ['', [Validators.required, Validators.minLength(8)]]
          phone: ['',[Validators.required,Validators.minLength(8)]]
      });
  }

  ngOnInit(): void {
  }

}
