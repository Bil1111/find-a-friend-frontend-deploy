import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-free-people',
  templateUrl: './free-people.component.html',
  styleUrls: ['./free-people.component.css']
})
export class FreePeopleComponent {
  // ДАНІ КОРИСТУВАЧА
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  contactNumber: string = '';
  shelter: string = '';
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private http: HttpClient) {}

  Volonter() {
    const volonterData = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      contactNumber: this.contactNumber,
      shelter: this.shelter
    };

    this.http.post('http://localhost:8080/api/forms/volunteer', volonterData).subscribe({
      next: (response) => {
        this.successMessage = 'Форма успішно відправлена!';
        this.errorMessage = null;
        console.log('Форма успішно відправлена', response);
        this.clearForm();
        this.clearMessagesAfterDelay();
      },
      error: (error) => {
        this.errorMessage = 'Сталася помилка під час відправлення форми.';
        this.successMessage = null;
        console.error('Сталася помилка', error);
        this.clearMessagesAfterDelay();
      }
    });
  }

  clearForm() {
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.contactNumber = '';
    this.shelter = '';
  }

  clearMessagesAfterDelay() {
    setTimeout(() => {
      this.errorMessage = null;
      this.successMessage = null;
    }, 5000); // Повідомлення зникнуть через 5 секунд
  }
}
