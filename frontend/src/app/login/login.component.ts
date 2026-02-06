import {Component, inject, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {LoginService} from '../login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  passwordForm: string = "";
  loginService: LoginService = inject(LoginService);
  router: Router = inject(Router);

  submit(): void {
    console.log(this.passwordForm);
    this.loginService.login(this.passwordForm).subscribe(response => {
      if (response === true) {
        sessionStorage.setItem('password', this.passwordForm);
        this.router.navigate(['/admin']);
      } else {
        alert("Wrong password");
      }
    }, error => {
      alert("Wrong password");
    })
  }

  ngOnInit(): void {

  }

}
