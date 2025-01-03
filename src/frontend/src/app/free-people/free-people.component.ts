import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-free-people',
  templateUrl: './free-people.component.html',
  styleUrl: './free-people.component.css'
})
export class FreePeopleComponent {
  // ДАНІ КОРИСТУВАЧА
  name: string = '';
  surname: string = '';
  email: string = '';
  phone: string = '';
  shelter: string = '';
  errorMessage: string | null = null;

  constructor(private http : HttpClient){

  }

  Volonter(){
    const volonterData =  {
      name: this.name,
      surname: this.surname,
      email: this.email,
      phone: this.phone,
      shelter: this.shelter
    };


    this.http.post('http://localhost:8080/api/users/login',volonterData).subscribe({
      next:(response)=>{
        console.error('Форма успішна відправилася', response);
      },
      error:(error)=>{
        this.errorMessage = 'Сталося помилка';
        console.error('Сталося помилка', error);
      }
    });
  }
}
