import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-donate',
  templateUrl: './donate.component.html',
  styleUrls: ['./donate.component.css']
})
export class DonateComponent implements OnInit {
  shelters: any[] = []; // Масив для зберігання всіх притулків
  selectedShelter: any = null; // Для зберігання вибраного притулку

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchShelters();
  }

  // Метод для отримання всіх притулків
  fetchShelters() {
    this.http.get<any[]>('http://localhost:8080/api/shelters').subscribe(
      data => {
        console.log('Received shelters data:', data); // Логування отриманих даних
        this.shelters = data.map(shelter => {
          // Генеруємо URL для зображення кожного притулку
          shelter.imageURL = `http://localhost:8080/images/shelters/${shelter.id}.png`;
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

  // Метод для закриття модального вікна
  closeModal() {
    this.selectedShelter = null; // Скидаємо вибраний притулок
  }
}
