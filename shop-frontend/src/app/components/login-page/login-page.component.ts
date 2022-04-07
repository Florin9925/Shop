import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form: FormGroup;
  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.form = Object();
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, [Validators.required]],
    });
  }
  isFieldValid(field: string) {
    return (
      !this.form.get(field)?.valid &&
      this.form.get(field)?.touched &&
      this.form.get(field)?.touched
    );
  }
  onSubmit() {
    if (this.form.valid) {
      console.log("All field are validated");
    } else {
      this.validateAllFormFields(this.form);
    }
  }
  goToRegister() {
    this.router
          .navigate(["/products"])
          .then((r) => console.log("Unable to navigate"));
  }
  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach((field) => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }
}
