import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-for-all-shelter',
  templateUrl: './for-all-shelter.component.html',
  styleUrl: './for-all-shelter.component.css'
})
export class ForAllShelterComponent implements OnInit {
  shelter: any = {};
  animals: any[] = [];
  filteredAnimals: any[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  itemsPerPage: number = 10;
  selectedAnimal: any = null; // Для зберігання вибраної тварини
  isAdoptFormOpen: any = null;
  isWardFormOpen: any = null;

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

  constructor(private http: HttpClient, private route: ActivatedRoute) {}
  shelter_Name: string = '';
  shelter_ID: number = 0;

  ngOnInit() {

      // Отримання значення `shelterName` із параметрів маршруту
      this.route.queryParams.subscribe(params => {
        this.shelter_Name = params['shelterName'];
        this.shelter_ID = +params['shelterId'];
        this.fetchShelterData(this.currentPage);
      });

  }

  // fetchShelterData(shelterID: number) {
  //   this.http.get<any>(`http://localhost:8080/api/shelters/${this.shelter_ID}`)
  //     .subscribe(
  //       response => {
  //         this.shelter = response;
  //         this.animals = response.animals || [];
  //
  //         // Генерація локального фото для кожної тварини
  //         this.animals = this.animals.map(animal => {
  //           // Якщо фото не надано, можна додати дефолтне фото або генерувати його за умовами
  //           animal.imageURL = `http://localhost:8080/images/${animal.id}.png`
  //           // Якщо потрібно додавати більше властивостей, ви можете це зробити тут
  //           return animal;
  //         });
  //
  //         this.filteredAnimals = [...this.animals];
  //         this.totalPages = response.totalPages; // Ensure your API returns totalPages
  //       },
  //       error => console.error('Error fetching shelter data:', error)
  //     );
  // }

  fetchShelterData(page: number) {
    const startId = (page - 1) * this.itemsPerPage;

    this.http.get<any[]>(`http://localhost:8080/api/shelters/${this.shelter_ID}/animals/next/${startId}`)
      .subscribe(
        response => {
          this.animals = response || []; // Тепер відповідь — це список тварин

          // Генерація локального фото для кожної тварини
          this.animals = this.animals.map(animal => {
            animal.imageURL = `http://localhost:8080/images/${animal.id}.png`;
            return animal;
          });

          this.filteredAnimals = [...this.animals];

          // Запит на отримання всіх тварин для обчислення загальної кількості сторінок
          this.http.get<any[]>(`http://localhost:8080/api/shelters/${this.shelter_ID}/animals`)
            .subscribe(
              allAnimals => {
                this.totalPages = Math.ceil(allAnimals.length / this.itemsPerPage); // Округлюємо до більшого
                console.log('Total pages:', this.totalPages);
              },
              error => {
                console.error('Error fetching all animals:', error);
              }
            );
        },
        error => console.error('Error fetching shelter data:', error)
      );
  }



  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.fetchShelterData(this.currentPage);
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.fetchShelterData(this.currentPage);
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

  // Змінні для фільтрів
  activeFilters: any = {
    type: [],
    sex: [],
    age: [],
    city: [],
    vakcin:[],
    steril:[],
    need_help:[]
  };

  toggleFilter(filterType: string, value: string | number) {
    const filterArray = this.activeFilters[filterType]; // Це масив, в якому зберігаються значення для вибраного фільтра.
    const index = filterArray.indexOf(value);
    if(value === '' || 0){ this.activeFilters[filterType] = [];}

    if (index > -1) {
      filterArray.splice(index, 1);// видаляє за допомогою методу splice.
    } else {
      filterArray.push(value); //додає його до масиву фільтрів
    }
  this.applyFilters(); // застосування нових фільтрів до колекції тварин.
  }

  applyFilters() {
    this.animals = this.filteredAnimals.filter(animal => {
      return (
        this.isMatchingFilter(animal, 'type', this.activeFilters.type) &&
        this.isMatchingFilter(animal, 'sex', this.activeFilters.sex) &&
        this.isMatchingFilter(animal, 'age', this.activeFilters.age) &&
        this.isMatchingFilter(animal, 'city', this.activeFilters.city) &&
        this.isMatchingFilter(animal, 'vakcin', this.activeFilters.vakcin) &&
        this.isMatchingFilter(animal, 'steril', this.activeFilters.steril) &&
        this.isMatchingFilter(animal, 'need_help', this.activeFilters.need_help)
      );
    });
  }

  isMatchingFilter(animal: any, filterType: string, activeValues: any[]): boolean {
    if (activeValues.length === 0 || activeValues.includes('')) {
      return true; // Якщо фільтр не активний, всі значення проходять
    }
    // if (filterType === 'type' && activeValues.includes('') || filterType === 'sex' && activeValues.includes('')
    //    || filterType === 'age' && activeValues.includes(0) || filterType === 'city' && activeValues.includes('')) {
    //   return true; // Вік "Усі" повинен проходити
    // }

    if (filterType === 'age') {
      const ageRanges: { [key: number]: (age: number) => boolean } = {
        1: (age) => age <= 1,
        2: (age) => age >= 1 && age <= 2,
        3: (age) => age >= 2 && age <= 5,
        4: (age) => age > 5,
      };

      return activeValues.some(value => ageRanges[value]?.(animal.age));
    }

    return activeValues.includes(animal[filterType]);
  }


 // ФОРМА ДЛЯ УСИНОВЛЕННЯ
  ModalAdopt(){
    const AdoptData= {
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
    console.log('Дані форми:', AdoptData);
    // const token = localStorage.getItem('token');
    //
    // // Перевіряємо, чи є токен
    // if (!token) {
    //   console.error('Token not found');
    //   return;
    // }
    //
    // const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    this.http.post('http://localhost:8080/api/forms/adopt', AdoptData).subscribe({
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
