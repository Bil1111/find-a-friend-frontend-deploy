import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-regest',
  templateUrl: './regest.component.html',
  styleUrls: ['./regest.component.css']
})
export class RegestComponent {
  email: string = '';
  password: string = '';
  passwordAgain: string = '';
  errorMessage: string | null = null;
  loading: boolean = false; // Додали змінну для завантаження

  constructor(private http: HttpClient, private router: Router) {}



  validateEmail(email: string): boolean {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
  }

  register() {
    if (!this.validateEmail(this.email)) {
      this.errorMessage = 'Невірний формат електронної пошти';
      console.error(this.errorMessage);
      return;
    }
    if (this.password !== this.passwordAgain) {
      this.errorMessage = 'Паролі не співпадають';
      console.error(this.errorMessage);
      return;
    }

    if (this.password=='') {
      this.errorMessage = 'Пароль не може бути пустим';
      console.error(this.errorMessage);
      return;
    }

    if (this.password.length<6) {
      this.errorMessage = 'Пароль має містити мінімум 6 символів';
      console.error(this.errorMessage);
      return;
    }



    const registrationData = {
      email: this.email,
      password: this.password
    };
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response' as 'response'
    };

    this.loading = true; // Включаємо стан завантаження

    this.http.post('http://localhost:8080/api/users/register', registrationData, httpOptions)
      .subscribe({
        next: (response) => {
          this.loading = false; // Вимикаємо стан завантаження
          console.log('Response status:', response.status);
          console.log('Response body:', response.body);
          if (response.status === 201) {
            //
            console.log('Реєстрація успішна', response);
            this.router.navigate(['/sing-in']);
            window.location.reload;
          } else {
            this.errorMessage = 'Сталася невідома помилка';
          }
        },
        error: (error) => {
          this.loading = false; // Вимикаємо стан завантаження
          console.error('Помилка:', error);
          if (error.status === 409) {
            this.errorMessage = 'Користувач з такою поштою вже існує';
          } else {
            this.errorMessage = 'Реєстрація не вдалася';
          }
        }

      });

  }

}
