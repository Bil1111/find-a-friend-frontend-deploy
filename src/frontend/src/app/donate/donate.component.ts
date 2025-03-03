import { Component, OnInit, HostListener } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-donate',
  templateUrl: './donate.component.html',
  styleUrls: ['./donate.component.css']
})
export class DonateComponent implements OnInit {

  isScrollToTopVisible: boolean = false;
  @HostListener('window:scroll',[])
  onWindowScroll() {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    this.isScrollToTopVisible = scrollTop > 300;
  }
    scrollToTop(){window.scrollTo({top:0,});}

  shelters: any[] = []; // Масив для зберігання всіх притулків
  selectedShelter: any = null; // Для зберігання вибраного притулку

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchShelters();
  }

  // Метод для отримання всіх притулків
  fetchShelters() {
    this.http.get<any[]>('https://find-a-friend-backend-deploy.onrender.com/api/shelters').subscribe(
      data => {
        console.log('Received shelters data:', data); // Логування отриманих даних
        this.shelters = data.map(shelter => {
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
