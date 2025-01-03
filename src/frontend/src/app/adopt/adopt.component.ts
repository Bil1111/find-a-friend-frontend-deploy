import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-adopt',
  templateUrl: './adopt.component.html',
  styleUrl: './adopt.component.css'
})
export class AdoptComponent {
 name: string = '';
 surname: string = '';
 email: string = '';
 phone: string = '';
 shelter: string = '';
 animal: string = '';
 exp: string = '';
 errorMessage: string | null = null;
 
   constructor(private http: HttpClient) {
  
     }

     FormAdopt(){
       const adoptData ={
        name: this.name,
        surname: this.surname,
        email: this.email,
        phone: this.phone,
        shelter: this.shelter,
        animal: this.animal,
        exp: this.exp
       };

       this.http.post('http://localhost:8080/api/udsers/lodgin', adoptData).subscribe({
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
