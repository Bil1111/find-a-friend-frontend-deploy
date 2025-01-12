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
  shelter: string = '';
  typeOfAnimal: string = '';
  experience: string = '';
  errorMessage: string | null = null;
  successMessage: string | null = null;


  shelters: any[] = []; // Масив для зберігання всіх притулків
  selectedShelter: any = null; // Для зберігання вибраного притулку
  animals: any[] = []; // Масив для зберігання всіх тварин
  allAnimals: any[] = []; // Для зберігання вибраної тварини
  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchShelters();
    this.fetchAnimals();
   }


 // Метод для отримання всіх тварин
   fetchAnimals() {

    this.http.get<any[]>(`http://localhost:8080/api/animals`).subscribe(
      data => {
        console.log('Received data:', data); // Додайте це логування
        this.allAnimals = data.map(animal => {
          // Генеруємо URL для зображення
          // animal.imageURL = `http://localhost:8080/images/${animal.id}.png`;
          return animal;
        });
                // Викликаємо метод для фільтрації після того, як отримали shelter_ID
          //  this.filterAnimals(this.shelter_ID);
        this.animals = [...this.allAnimals]; // Ініціалізуємо тварин

      },
      error => {
        console.error('Error fetching animals:', error); // Логування помилки
      }
    );
  }


  // Метод для отримання всіх притулків
  fetchShelters() {
    this.http.get<any[]>('http://localhost:8080/api/shelters').subscribe(
      data => {
        console.log('Received shelters data:', data); // Логування отриманих даних
        this.shelters = data.map(shelter => {
          // Генеруємо URL для зображення кожного притулку
          // shelter.imageURL = `http://localhost:8080/images/shelters/${shelter.id}.png`;
          return shelter;
        });
      },
      error => {
        console.error('Error fetching shelters:', error); // Логування помилки
      }
    );
  }

  // Метод для вибору притулку
  selectShelter(shelter: any) {
    this.selectedShelter = shelter; // Зберігаємо вибраний притулок
  }




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
