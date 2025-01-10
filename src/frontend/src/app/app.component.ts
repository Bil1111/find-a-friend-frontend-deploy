import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SharedService } from './shared.service';
declare const google: any;

interface MapPoint {
  id: number;
  name: string;
  address: string;
  contactNumber: string;
  description: string;
  city: string;
  latitude: number;
  longitude: number;
  imageURL: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})
export class AppComponent implements OnInit {
 // searcshelter: string = '';// Зберігає значення поля введення

  title = 'FF';
  isHomePage: boolean = false;
  mapPoints: MapPoint[] = [];

  //
    isLogged: boolean = false;
   ShowFooter: boolean = false;

  // log: boolean = false; // Для тесту

  visible = false;
  constructor(private router: Router, private http: HttpClient,private sharedService: SharedService) {
    this.router.events.subscribe(() => {
      this.ShowFooter = this.router.url !== '/adopt' &&  this.router.url !== '/gifthouse' &&  this.router.url !== '/free-people' && this.router.url !== '/gifthouse' ;

    })
    // Підписка на зміну стану логіну
    this.sharedService.isLoggedIn$.subscribe(isLoggedIn =>{
      this.isLogged = isLoggedIn;
    });
  }

  ngOnInit() {
    this.router.events.subscribe(() => {
      this.isHomePage = this.router.url === '/';
      if (this.isHomePage) {
        this.loadMapPoints();
      }
    });
}
    // Для знаходження притулків
  //   findshelter(){
  //     this.http.get('http://localhost:8080/api/shelters/search?query=' + this.searcshelter).subscribe({
  //       next: (response) => {
  //         console.log('Результати пошуку:', response);
  //       },
  //       error: (error) => {
  //         console.error('Помилка при пошуку:', error);
  //       },
  //     });
  // }

  loadMapPoints() {
    this.http.get<MapPoint[]>('http://localhost:8080/api/mapPoints')
      .subscribe(data => {
        this.mapPoints = data;
        this.initMap();
      });
  }

  initMap() {
    const map = new google.maps.Map(document.getElementById('map') as HTMLElement, {
      zoom: 10,
      center: { lat: 50.4501, lng: 30.5234 }, // Центр карти - Київ
    });

    this.mapPoints.forEach(point => {
      const marker = new google.maps.Marker({
        position: { lat: point.latitude, lng: point.longitude }, // Використовуємо latitude та longitude
        map: map,
        title: point.name,
      });

      // Створення інфо-вікна для мітки
      const infoWindow = new google.maps.InfoWindow({
        content: `
          <div>
            <h2>${point.name}</h2>
            <p><strong>ID:</strong> ${point.id}</p>
            <p><strong>Місто:</strong> ${point.city}</p>
            <p><strong>Адреса:</strong> ${point.address}</p>
            <p><strong>Телефон:</strong> ${point.contactNumber}</p>
            <p><strong>Опис:</strong> ${point.description}</p>
            <img src="${point.imageURL}" alt="${point.name}" style="width:100px;height:auto;">
          </div>
        `,
      });

      // Обробник події для відкриття інфо-вікна
      marker.addListener('click', () => {
        infoWindow.open(map, marker);
      });
    });
  }


  closeMenu(){this.visible = false;}
  Openmemu(){this.visible = true;}


  logout() {
    // Remove token from localStorage on logout
    localStorage.removeItem('token');
    this.sharedService.changeLoginState(false);
  }

}
