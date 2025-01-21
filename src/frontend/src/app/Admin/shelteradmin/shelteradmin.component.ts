import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-shelteradmin',
  templateUrl: './shelteradmin.component.html',
  styleUrl: './shelteradmin.component.css'
})
export class ShelteradminComponent {
 Shelters: any[] = [];
 selectedShelter: any = null; // Для зберігання вибраного притулку
 // МОДАЛЬНЕ ВІКНО ДЛЯ ПРИТУЛКА
 name: string = '';
 city: string = '';
 address: string = '';
 telephon: string = '';

constructor(private http: HttpClient) { }

ngOnInit() {
  this.SherlterForAdmin();
}

SherlterForAdmin() {
  this.http.get<any[]>('http://localhost:8080/api/shelters').subscribe(
    data => {
      this.Shelters = data.map(shelter => { 
        
        return shelter;}); 
    },
    error => {
      console.error('Error fetching shelters:', error); // Логування помилки
    }
  );
}

selectShelter(shelter: any){
  this.selectedShelter = shelter;
  this.name = shelter.name;
  this.city = shelter.city;
  this.address = shelter.address;
  this.telephon = shelter.contactNumber;
}
closeModal() {
  this.selectedShelter = null; // Скидаємо вибраний притулок
}

SendEditedShelter(){
  const EditShelter = {
     id: this.selectedShelter.id,
     name: this.name,
     city: this.city,
     address: this.address,
     telephon: this.telephon
  };

  this.http.put(`http://localhost:8080/api/shelters/update/${EditShelter.id}`,EditShelter).subscribe(
   {next: (response) => {
    console.log('Притулок оновлено успішно', response);
    this.closeModal();
  },
  error: (error) => {
    console.error('Помилка при оновленні притулку', error);
    
  }})

}
}
