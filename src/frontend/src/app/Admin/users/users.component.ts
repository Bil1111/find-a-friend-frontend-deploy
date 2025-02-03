import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  Users: any [] = [];
  searchInfo: boolean = false;
  selectedUser: any = null;
  addform: boolean = false;

  Id: number = 0;
  email: string = '';
  password: string ='';
  role: string ='';

  search_data_id: any = {id:''};
  search_user_email: any = {email:''};
  search_user_password: any = {password:''};
  search_user_role: any = {role:''};


  emailadmin: string = '';
  passwordadmin: string = '';
  passwordAgain: string = '';
  errorMessage: string | null = null;
  loading: boolean = false;


  constructor(private http: HttpClient, private router: Router) {}
  ngOnInit(){
    this.AllUsers();
  }

  AllUsers(){
    this.http.get<any[]>('http://localhost:8080/api/users').subscribe(
      data =>{this.Users = data.map( user => {  return user;});}, error =>{   console.error('Error fetching shelters:', error);});
  }

  OpenSearchForm(){this.searchInfo = true;}
  closeSearch(){this.searchInfo =false;}
  CleanSearchForm(){
    this.search_data_id.id = '';
    this.search_data_id.email = '';
    this.search_data_id.password = '';
    this.search_data_id.role = '';
  }

  // selectUser(user:any){
  //  this. selectedUser = user;
  //  this.Id = user.id;
  //  this.email = user.email;
  //  this.password = user.password;
  //  this.role = user.role;
  // }
  closeModal(){this.selectedUser = null;}

  // SendEditedUser(){

  //   const datauser = {
  //     id: this.Id,
  //     email: this.email,
  //     password: this.password,
  //     role: this.role
  //   };

  //   this.http.put<any[]>(`http://localhost:8080/api/users/update/${datauser.id}` , datauser , {responseType: 'text' as 'json'}).subscribe(
  //   {next: (response) => {
  //     console.log(response);
  //     this.closeModal();
  //     this.AllUsers();
  //   },
  //   error: (error) => {
  //     console.error('Помилка при оновленні користувача', error);

  //   }})
  // }

  OpenAddForm(){this.addform = true};
  closeAdd(){this.addform = false};


    register() {
      if (this.password !== this.passwordAgain) {
        this.errorMessage = 'Паролі не співпадають';
        console.error(this.errorMessage);
        return;
      }

      const registrationData = {
        email: this.email,
        password: this.password
      };
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        }),
        observe: 'response' as 'response'
      };

      this.loading = true; // Включаємо стан завантаження

      // this.http.post('http://localhost:8080/api/users/register', registrationData, httpOptions)
      //   .subscribe({
      //     next: (response) => {
      //       this.loading = false; // Вимикаємо стан завантаження
      //       console.log('Response status:', response.status);
      //       console.log('Response body:', response.body);
      //       if (response.status === 201) {
      //         //
      //         console.log('Реєстрація успішна', response);
      //         this.router.navigate(['/sing-in']);
      //         window.location.reload;
      //       } else {
      //         this.errorMessage = 'Сталася невідома помилка';
      //       }
      //     },
      //     error: (error) => {
      //       this.loading = false; // Вимикаємо стан завантаження
      //       console.error('Помилка:', error);
      //       if (error.status === 409) {
      //         this.errorMessage = 'Користувач з такою поштою вже існує';
      //       } else {
      //         this.errorMessage = 'Реєстрація не вдалася';
      //       }
      //     }

      //   });

    }


}
