import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface MapPoint {
  name: string;
  address: string;
  phone: string;
  lat: number;
  lng: number;
  image: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] // corrected styleUrl to styleUrls
})
export class AppComponent implements OnInit {
  title = 'FF';
  isHomePage: boolean = false;
  mapPoints: MapPoint[] = [];

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit() {
    this.router.events.subscribe(() => {
      this.isHomePage = this.router.url === '/';
      if (this.isHomePage) {
        this.loadMapPoints();
      }
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
      center: { lat: 50.4501, lng: 30.5234 }, // Center the map to Kyiv
    });

    this.mapPoints.forEach(point => {
      const marker = new google.maps.Marker({
        position: { lat: point.lat, lng: point.lng },
        map: map,
        title: point.name,
      });

      // Create an info window for the marker
      const infoWindow = new google.maps.InfoWindow({
        content: `
          <div>
            <h2>${point.name}</h2>
            <p>${point.address}</p>
            <p>Телефон: ${point.phone}</p>
            <img src="${point.image}" alt="${point.name}" style="width:100px;height:auto;">
          </div>
        `,
      });

      // Add a click listener to the marker
      marker.addListener('click', () => {
        infoWindow.open(map, marker);
      });
    });
  }
}
