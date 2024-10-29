import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'FF';
  isHomePage: boolean = false;
  map!: google.maps.Map; // Використовуємо оператор запевнення визначення (!)

  interface Shelter {
  name: string;
  address: string;
  phone: string;
  lat: number;
  lng: number;
  image: string;

  ngOnInit(): void {
  }
}

constructor(private router: Router, private http: HttpClient) {}

ngOnInit() {
  this.router.events.subscribe(() => {
    this.isHomePage = this.router.url === '/';
    if (this.isHomePage) {
      this.loadMap();
    }
  });
}

loadMap() {
  const mapOptions: google.maps.MapOptions = {
    center: { lat: 50.4501, lng: 30.5234 }, // Центр карти
    zoom: 12
  };

  this.map = new google.maps.Map(document.getElementById('map') as HTMLElement, mapOptions);

  // Отримати мітки з API
  this.fetchShelters();
}

fetchShelters() {
  this.http.get<Shelter[]>('http://localhost:8080/api/mapPoints').subscribe(
    (shelters: Shelter[]) => {
      this.addMarkers(shelters);
    },
    (error) => {
      console.error('Error fetching shelters:', error);
    }
  );
}

addMarkers(shelters: Shelter[]) {
  shelters.forEach(shelter => {
    const marker = new google.maps.Marker({
      position: { lat: shelter.lat, lng: shelter.lng },
      map: this.map,
      title: shelter.name,
    });

    // Додаємо слухача подій для мітки
    marker.addListener('click', () => {
      alert(`Ви клікнули на ${shelter.name}\nАдреса: ${shelter.address}\nТелефон: ${shelter.phone}`); // Виводимо деталі закладу
    });
  });
}

searchShelter() {
  const shelterName = (document.getElementById('name') as HTMLInputElement).value;
  // Реалізуйте логіку пошуку на основі назви притулку
  console.log(`Шукаємо заклад: ${shelterName}`);
}
}
