import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  form: FormGroup;
  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.form = Object();
  }
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      firstName: [null, Validators.required],
      lastName: [null, [Validators.required]],
      email: [null, [Validators.required]],
      password: [null, [Validators.required]],
      address: [null, [Validators.required]],
      telephone: [null, [Validators.required]],
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
      this.router
      .navigate([""])
      .then((r) => console.log("Unable to navigate"));
    } else {
      this.validateAllFormFields(this.form);
    }
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
