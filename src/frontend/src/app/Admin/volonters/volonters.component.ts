import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-volonters',
  templateUrl: './volonters.component.html',
  styleUrl: './volonters.component.css'
})
export class VolontersComponent {
  Volonter: any [] = [];
  searchInfo: boolean = false;
  selectedVolonter: any = null;

  Id: number = 0;
  email: string = '';
  contactnumber: string = '';
  name: string = '';
  surname: string = '';
  shelter: string = '';

  search_data_id: any = {id:''};
  search_user_email: any = {email:''};
  search_user_contactNumber: any = {contactNumber:''};
  search_user_firstName: any = {firstName:''};
  search_user_lastName: any = {lastName:''};
  search_user_shelter: any = {rolsheltere:''};


  constructor(private http : HttpClient){}
  ngOnInit(){
    this.AllUsers();
  }

  AllUsers() {
    this.http.get<any[]>('http://localhost:8080/api/forms/volunteer/all').subscribe(
      data => {
        console.log("API Response:", data);  // Логуємо всю відповідь від API
        this.Volonter = data.map(volonter => { 
          console.log(volonter.shelter);  // Логуємо кожного волонтера окремо
          return volonter;
        });
      },
      error => {
        console.error('Error fetching shelters:', error);
      });
  }

  OpenSearchForm(){this.searchInfo = true;}
  closeSearch(){this.searchInfo =false;}
  CleanSearchForm(){
    this.search_data_id.id = '';
    this.search_data_id.contactNumber = '';
    this.search_data_id.email = '';
    this.search_data_id.firstName = '';
    this.search_data_id.lastName = '';
    // this.search_data_id.shelter = '';
  }

  selectUser(volonter:any){
   this.selectedVolonter = volonter;

   this.Id = volonter.id;
   this.email = volonter.email;
   this.contactnumber = volonter.contactNumber;
   this.name = volonter.firstName;
   this.surname = volonter.lastName;
   this.shelter = volonter.shelter;
  }
  closeModal(){this.selectedVolonter = null;}

  SendEditedUser(){

    const DataVolonter = {
      id: this.Id,
      email:  this.email,
      contactNumber: this.contactnumber,
      firstName: this.name,
      lastName:  this.surname
    };

    // this.http.put<any[]>(`http://localhost:8080/api/forms/volunteer` , DataVolonter , {responseType: 'text' as 'json'}).subscribe(
    // {next: (response) => {
    //   // console.log(response); 
    //   this.closeModal();
    //   this.AllUsers();
    // },
    // error: (error) => {
    //   console.error('Помилка при оновленні користувача', error);
      
    // }})
  }
}
