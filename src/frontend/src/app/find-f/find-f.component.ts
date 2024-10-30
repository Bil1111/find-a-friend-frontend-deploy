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

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchAnimals(this.currentPage);
  }

  fetchAnimals(page: number) {
    const startId = (page - 1) * this.itemsPerPage;
    this.http.get<any[]>(`http://localhost:8080/api/animals/next/${startId}`).subscribe(
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
  }

  // Метод для закриття модального вікна
  closeModal() {
    this.selectedAnimal = null; // Скидаємо вибрану тварину
  }
}
