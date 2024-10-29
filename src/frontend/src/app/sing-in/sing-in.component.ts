import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingINComponent {
  email: string = '';
  password: string = '';
  errorMessage: string | null = null;

  constructor(private http: HttpClient, private router: Router) { }

  // Метод для відправки даних входу
  login() {
    const loginData = {
      email: this.email,
      password: this.password
    };

    this.http.post('http://localhost:8080/api/users/login', loginData)
      .subscribe({
        next: (response) => {
          console.log('Вхід успішний', response);
          this.router.navigate(['/about']); // Перенаправлення після успішного входу
        },
        error: (error) => {
          this.errorMessage = 'Вхід не вдався';
          console.error('Вхід не вдався', error);
        }
      });
  }
}
