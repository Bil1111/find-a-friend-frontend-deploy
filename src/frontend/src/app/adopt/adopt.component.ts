import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-adopt',
  templateUrl: './adopt.component.html',
  styleUrls: ['./adopt.component.css']
})
export class AdoptComponent implements OnInit {
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  contactNumber: string = '';
  shelter:  string = ''; //id притулку
  experience: string = '';
  errorMessage: string | null = null;
  successMessage: string | null = null;

  animalName: string ='';
  animalAge: string = '';
  animalSex: string = '';
  animalSize: string = '';
  typeOfAnimal: string = '';

  shelters: any[] = []; // Масив для зберігання всіх притулків
  // selectedShelter: any = null; // Для зберігання вибраного притулку
  animals: any[] = []; // Масив для зберігання всіх тварин
  allAnimals: any[] = []; // Для зберігання вибраної тварини
  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchShelters();
    this.fetchShelterData();
    // this.fetchAnimals();

   }



   fetchShelterData() {
    this.http.get<any>(`http://localhost:8080/api/shelters/${this.shelter}`)
      .subscribe(
        response => {
          this.shelter = response.id;
          this.animals = response.animals || [];
          this.allAnimals = [...this.animals];
        },
        error => console.error('Error fetching shelter data:', error)
      );
  }


 // Метод для отримання всіх тварин
   fetchAnimals() {
    this.http.get<any[]>(`http://localhost:8080/api/animals`).subscribe(
      data => {
        console.log('Received data:', data); // Додайте це логування
        this.allAnimals = data.map(animal => {
          return animal; });
        this.animals = [...this.allAnimals]; // Ініціалізуємо тварин
      },
      error => {console.error('Error fetching animals:', error);}
    );
  }


  // Метод для отримання всіх притулків
  fetchShelters() {
    this.http.get<any[]>('http://localhost:8080/api/shelters').subscribe(
      data => {
        console.log('Received shelters data:', data); // Логування отриманих даних
        this.shelters = data.map(shelter => {return shelter;});
      },
      error => {console.error('Error fetching shelters:', error); }
    );
  }

      hidden_info_for_animals(selectedAnimalName: string){
        const selectedAnimal = this.animals.find(animal => animal.name === selectedAnimalName);
        if(selectedAnimal){
          this.animalAge = selectedAnimal.age;
          this.animalSex = selectedAnimal.sex;
          this.animalSize = selectedAnimal.size;
          this.typeOfAnimal = selectedAnimal.type;
          this.animalName=selectedAnimal.name;
        }
      }

  FormAdopt() {
    const adoptData = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      contactNumber: this.contactNumber,
      experience: this.experience,
      shelter: this.shelter,

      animalAge: this.animalAge,
      animalSex: this.animalSex,
      animalSize: this.animalSize,
      typeOfAnimal: this.typeOfAnimal,
      animalName: this.animalName
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
    this.shelter = "";

   this.animalAge = '';
   this.animalSex = '';
    this.animalSize  = '';
    this.typeOfAnimal = '';
  }

  clearMessagesAfterDelay() {
    setTimeout(() => {
      this.errorMessage = null;
      this.successMessage = null;
    }, 5000); // Повідомлення зникне через 5 секунд
  }
}
