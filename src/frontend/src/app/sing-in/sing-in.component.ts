import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingINComponent implements OnInit {
  email: string = '';
  password: string = '';
  errorMessage: string | null = null;
  isLoggedIn: boolean = false;

  constructor(private http: HttpClient, private router: Router, private sharedService: SharedService) {
    // Підписка на зміну стану логіну
    this.sharedService.isLoggedIn$.subscribe(isLoggedIn =>{
      this.isLoggedIn = isLoggedIn;
    });
  }

  ngOnInit() {
    // Check if token exists in localStorage on component initialization
    if (localStorage.getItem('token')) {
      //this.isLoggedIn = true;
      this.sharedService.changeLoginState(true);
    }
  }
  // Login method
  login() {
    const loginData = {
      email: this.email,
      password: this.password
    };

    this.http.post('http://localhost:8080/api/users/login', loginData)
      .subscribe({
        next: (response: any) => {
          console.log('Login successful', response);

          // Збереження токена в localStorage
          localStorage.setItem('token', response.token);

          // Оновлення стану логіну
          this.sharedService.changeLoginState(true);

          // Перенаправлення користувача на сторінку, вказану сервером
          const redirectUrl = response.redirect || '/'; // Fallback, якщо redirect не вказано
          this.router.navigate([redirectUrl]);
        },
        error: (error) => {
          this.errorMessage = 'Login failed';
          console.error('Login failed', error);
        }
      });


  }

  // logout() {
  //   // Remove token from localStorage on logout
  //   localStorage.removeItem('token');
  //   this.sharedService.changeLoginState(false);
  //   //this.isLoggedIn = false; // Set isLoggedIn to false on logout
  // }

  // Метод для відправки даних входу
  // login() {
  //   const loginData = {
  //     email: this.email,
  //     password: this.password
  //   };
  //
  //   this.http.post('http://localhost:8080/api/users/login', loginData)
  //     .subscribe({
  //       next: (response) => {
  //         console.log('Вхід успішний', response);
  //         this.router.navigate(['/about']); // Перенаправлення після успішного входу
  //         window.location.reload;
  //       },
  //       error: (error) => {
  //         this.errorMessage = 'Вхід не вдався';
  //         console.error('Вхід не вдався', error);
  //       }
  //     });
  // }
}
