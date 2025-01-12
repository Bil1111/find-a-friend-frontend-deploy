import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-find-f',
  templateUrl: './find-f.component.html',
  styleUrls: ['./find-f.component.css']
})
export class FindFComponent implements OnInit {
  animals: any[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  itemsPerPage: number = 10;
  selectedAnimal: any = null; // Для зберігання вибраної тварини
  isAdoptFormOpen: any = null;
  isWardFormOpen: any = null;

  // Це зміннІ, яку буде використовувати [(ngModel)]
  //КОРИСТУВАЧ
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  contactNumber: string = '';
  experience: string ='';
  errorMessage: string | null = null;
  successMessage: string | null = null;
  //ТВАРИНКА
  animalName: string ='';
  animalAge: string ='';
  animalSex: string ='';
  animalSize: string ='';
  typeOfAnimal: string ='';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchAnimals(this.currentPage);

  }

  fetchAnimals(page: number) {
    const startId = (page - 1) * this.itemsPerPage;
    this.http.get<any[]>(`http://localhost:8080/api/animals?start=${startId}&limit=${this.itemsPerPage}`).subscribe(
      data => {
        console.log('Received data:', data); // Додайте це логування
        this.animals = data.map(animal => {
          // Генеруємо URL для зображення
          animal.imageURL = `http://localhost:8080/images/${animal.id}.png`;
          return animal;
        });
        this.totalPages = Math.ceil(data.length / this.itemsPerPage); // Обчислюйте загальну кількість сторінок
      },
      error => {
        console.error('Error fetching animals:', error); // Логування помилки
      }
    );
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.fetchAnimals(this.currentPage);
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.fetchAnimals(this.currentPage);
    }
  }

  // Метод для вибору тварини
  selectAnimal(animal: any) {
    this.selectedAnimal = animal; // Зберігаємо вибрану тварину
    this.isAdoptFormOpen = null;
    this.isWardFormOpen = null;
  }

  openAdoptForm(){
    this.isAdoptFormOpen = this.selectedAnimal;
    this.animalName = this.selectedAnimal.name || 'empty';
    this.animalAge = this.selectedAnimal.age || 'empty';
    this.animalSex = this.selectedAnimal.sex || 'empty';
    this.animalSize = this.selectedAnimal.size || 'empty';
    this.typeOfAnimal = this.selectedAnimal.type || 'empty';
  }

  openWardForm(){
    this.isWardFormOpen = this.selectedAnimal;

    // Ініціалізуємо значення для прихованих полів ДОДАВ ОЦЕ
    this.animalName = this.selectedAnimal.name || 'empty';
    this.animalAge = this.selectedAnimal.age || 'empty';
    this.animalSex = this.selectedAnimal.sex || 'empty';
    this.animalSize = this.selectedAnimal.size || 'empty';
    this.typeOfAnimal = this.selectedAnimal.type || 'empty';
  }
  // Метод для закриття модального вікна
  closeModal() {
    this.selectedAnimal = null; // Скидаємо вибрану тварину

  }
  closeAdoptForm(){
    this.isAdoptFormOpen = null;
  }
  closeWardForm(){
    this.isWardFormOpen = null;
  }

  ModalAdopt(){
    const WardData= {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      contactNumber: this.contactNumber,
      experience: this.experience,
      typeOfAnimal: this.typeOfAnimal,

      animalName: this.animalName,
      animalAge: this.animalAge,
      animalSex: this.animalSex,
      animalSize: this.animalSize,
    };

    this.http.post('http://localhost:8080/api/forms/adopt', WardData).subscribe({
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

  // ФОРМА ДЛЯ ОПІКИ
  ModalWard(){
    const WardData= {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      contactNumber: this.contactNumber,
      experience: this.experience,
      typeOfAnimal: this.typeOfAnimal,

      animalName: this.animalName,
      animalAge: this.animalAge,
      animalSex: this.animalSex,
      animalSize: this.animalSize,
    };

    this.http.post('http://localhost:8080/api/forms/ward', WardData).subscribe({
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
  }

  clearMessagesAfterDelay() {
    setTimeout(() => {
      this.errorMessage = null;
      this.successMessage = null;
    }, 5000); // Повідомлення зникне через 5 секунд
  }


}
