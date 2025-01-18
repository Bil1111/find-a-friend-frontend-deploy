import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { query } from '@angular/animations';
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
  searcshelter: string = '';// Зберігає значення поля введення

  title = 'FF';
  isHomePage: boolean = false;
  mapPoints: MapPoint[] = [];

  //
   public isLogged: boolean = false;
   ShowFooter: boolean = false;

  // log: boolean = false; // Для тесту

  visible = false;
  constructor(private router: Router, private http: HttpClient) {
    this.router.events.subscribe(() => {
      this.ShowFooter = this.router.url !== '/adopt' &&  this.router.url !== '/gifthouse' &&  this.router.url !== '/free-people';

    })
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
  findshelter(){
   this.http.get('http://localhost:8080/api/shelters/search?query=' + this.searcshelter).subscribe({
    next: (response) => {
      console.log('Результати пошуку:', response);
    },
    error: (error) => {
      console.error('Помилка при пошуку:', error);
    },
  });

  }

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
          <div  style="font-family: 'e-ukr' max-width: 300px; padding: 10px; margin: 0;border-radius: 10px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);" >
           <a id="shelter-link" href="/for-all-shelter?shelterName=${point.name}&shelterId=${point.id}" style="font-size: 2.3em;font-weight: bold;text-decoration: none;  display: flex;  justify-content: center;
             color: black; ">${point.name}</a>
            <p style=" margin-top: 10px; font-size: 1.1rem">  <strong>Місто:</strong> ${point.city}</p>
            <p style="font-size: 1.1rem" ><strong>Адреса:</strong> ${point.address}</p>
            <p style="font-size: 1.1rem"><strong>Телефон:</strong> ${point.contactNumber}</p>
            <p style="font-size: 1.1rem"><strong>Опис:</strong> ${point.description}</p>
            <img src="${point.imageURL}" alt="${point.name}" style="width:100px; height:auto; object-fit: cover;  ">
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


  logout(){
    // this.sharedService.logout();
   }



}
