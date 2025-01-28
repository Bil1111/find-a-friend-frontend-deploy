import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  Users: any [] = [];
  searchInfo: boolean = false;
  selectedUser: any = null;

  Id: number = 0;
  email: string = '';
  password: string ='';
  role: string ='';

  search_data_id: any = {id:''};
  search_user_email: any = {email:''};
  search_user_password: any = {password:''};
  search_user_role: any = {role:''};


  constructor(private http : HttpClient){}
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
}
