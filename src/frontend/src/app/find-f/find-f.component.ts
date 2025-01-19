import { Component, OnInit, HostListener  } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-find-f',
  templateUrl: './find-f.component.html',
  styleUrls: ['./find-f.component.css']
})
export class FindFComponent implements OnInit {

  isScrollToTopVisible: boolean = false;
  @HostListener('window:scroll',[])
  onWindowScroll() {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    this.isScrollToTopVisible = scrollTop > 300;
  }
    scrollToTop(){window.scrollTo({top:0,});}


  shelters: any[] = [];
  animals: any[] = [];
  AllAnimals: any[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  itemsPerPage: number = 10;
  selectedAnimal: any = null; // Для зберігання вибраної тварини
  isAdoptFormOpen: any = null;
  isWardFormOpen: any = null;

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
  Shelter: string= '';
  City: string ='';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    // this.fetchAnimals(this.currentPage);
    this.fetchAllShelterAnimals(this.currentPage);
  }

  // fetchAnimals(page: number) {
  //   const startId = (page - 1) * this.itemsPerPage;
  //   this.http.get<any[]>(`http://localhost:8080/api/animals?start=${startId}&limit=${this.itemsPerPage}`).subscribe(
  //     data => {
  //       console.log('Received data:', data); // Додайте це логування
  //       this.AllAnimals = data.map(animal => {
  //         // Генеруємо URL для зображення
  //         animal.imageURL = `http://localhost:8080/images/${animal.id}.png`;
  //         return animal;
  //       });
  //       this.animals = [...this.AllAnimals]; // Ініціалізуємо тварин
  //       this.totalPages = Math.ceil(data.length / this.itemsPerPage); // Обчислюйте загальну кількість сторінок
  //     },
  //     error => {
  //       console.error('Error fetching animals:', error); // Логування помилки
  //     }
  //   );
  // }
  // this.animals = [];&& Array.isArray(shelter.animals)

  fetchAllShelterAnimals(page: number) {
    const startId = (page - 1) * this.itemsPerPage;
    this.http.get<any[]>('http://localhost:8080/api/shelters') // Отримуємо всі притулки
      .subscribe(
        shelters => { shelters.forEach(shelter => {
            if (shelter.animals) {
              const animalsWithShelterName = shelter.animals.map((animal: any) => ({
                ...animal, // копіювання всіх властивостей об'єкта
                shelterName: shelter.name, // Додаємо назву притулку до кожної тварини
                imageURL: `http://localhost:8080/images/${animal.id}.png` // Генеруємо URL для зображення на основі ID тварини
              }));
              this.animals.push(...animalsWithShelterName); // Додаємо до загального списку
            }
          });
          this.AllAnimals = [...this.animals]; // Зберігаємо копію для подальшої фільтрації чи обробки
        },
        error => console.error('Error fetching shelter data:', error)
      );
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.fetchAllShelterAnimals(this.currentPage);
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.fetchAllShelterAnimals(this.currentPage);
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
         this.Shelter = this.selectedAnimal.shelterName || 'empty';
         this.City = this.selectedAnimal.city || 'empty';
  }

 openWardForm(){
    this.isWardFormOpen = this.selectedAnimal;
    this.animalName = this.selectedAnimal.name || 'empty';
    this.animalAge = this.selectedAnimal.age || 'empty';
    this.animalSex = this.selectedAnimal.sex || 'empty';
    this.animalSize = this.selectedAnimal.size || 'empty';
    this.typeOfAnimal = this.selectedAnimal.type || 'empty';
             this.Shelter = this.selectedAnimal.shelterName || 'empty';
             this.City = this.selectedAnimal.city || 'empty';
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
    this.animals = this.AllAnimals.filter(animal => {
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

      Shelter: this.Shelter,
      City: this.City,
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
