import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

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

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    // Check if token exists in localStorage on component initialization
    if (localStorage.getItem('token')) {
      this.isLoggedIn = true;
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
          // Store token in localStorage
          localStorage.setItem('token', response.token);
          this.isLoggedIn = true; // Set isLoggedIn to true after successful login
          this.router.navigate(['/about']); // Redirect after login
        },
        error: (error) => {
          this.errorMessage = 'Login failed';
          console.error('Login failed', error);
        }
      });
  }

  logout() {
    // Remove token from localStorage on logout
    localStorage.removeItem('token');
    this.isLoggedIn = false; // Set isLoggedIn to false on logout
    this.router.navigate(['/login']);
  }

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
