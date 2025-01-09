import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-adopt',
  templateUrl: './adopt.component.html',
  styleUrls: ['./adopt.component.css']
})
export class AdoptComponent {
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  contactNumber: string = '';
  typeOfAnimal: string = '';
  experience: string = '';
  shelter: string = '';
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private http: HttpClient) {}

  FormAdopt() {
    const adoptData = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      contactNumber: this.contactNumber,
      typeOfAnimal: this.typeOfAnimal,
      experience: this.experience,
      shelter: this.shelter
    };

    this.http.post('http://localhost:8080/api/forms/adopt', adoptData).subscribe({
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
    this.typeOfAnimal = '';
    this.experience = '';
    this.shelter = '';
  }

  clearMessagesAfterDelay() {
    setTimeout(() => {
      this.errorMessage = null;
      this.successMessage = null;
    }, 5000); // Повідомлення зникне через 5 секунд
  }
}
