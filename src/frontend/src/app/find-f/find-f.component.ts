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
  // isAdoptFormOpen: boolean = false;
  //  isWardFormOpen: boolean = false;
  isAdoptFormOpen: any = null;
  isWardFormOpen: any = null;
 
  // Це зміннІ, яку буде використовувати [(ngModel)]
  //КОРИСТУВАЧ
  name: string = '';
  surname: string = '';
  email: string = '';
  phone: string = '';
  exp: string ='';
  //ТВАРИНКА
  namePet: string ='';
  AgePet: string ='';
  SexPet: string ='';
  SizePet: string ='';
  TypePet: string ='';
  // shelter: string= '';
  City: string ='';

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
  }

 openWardForm(){
    this.isWardFormOpen = this.selectedAnimal;
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

 // ФОРМА ДЛЯ УСИНОВЛЕННЯ
  ModalAdopt(){

  }

   // ФОРМА ДЛЯ ОПІКИ
   ModalWard(){

   }
 
}
