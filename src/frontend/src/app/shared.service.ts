// import { Injectable } from '@angular/core';

// @Injectable({
//   providedIn: 'root'
// })
// export class SharedService {

//   private _isLoggedIn: boolean = false;
//   private _userKey: string = ''; // Індивідуальний ключ для кожного користувача.

//   constructor() { }

//   setUserKey(userId?: string): void {
//     if (userId) {
//       // Використовуємо унікальний ідентифікатор користувача
//       this._userKey = `_isLoggedIn_${userId}`;
//     } else {
//       // Генеруємо ключ автоматично, якщо ідентифікатор не переданий
//       this._userKey = `_isLoggedIn_${this.generateUUID()}`;
//     }
//   }

//    getisLoggedIn(): boolean{
//     const key = localStorage.getItem(this._userKey);
//     this._isLoggedIn = key ? JSON.parse(key) : false;
//     return this._isLoggedIn;
//   }

//   setisLoggedIn(value : boolean): void{
//     if (!this._userKey) {
//       throw new Error('User key is not set. Use setUserKey() before calling setisLoggedIn.');
//     }
//     this._isLoggedIn = value;
//     localStorage.setItem(this._userKey, JSON.stringify(value));
//   }

//   logout() {
//     this.setisLoggedIn(false);
//   }

//   private generateUUID(): string {
//     return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
//       const r = (Math.random() * 16) | 0;
//       const v = c === 'x' ? r : (r & 0x3) | 0x8;
//       return v.toString(16);
//     });
//   }

//   // private _isLoggedInKey = 'isLoggedIn_';


//   // constructor() {}

//   // getisLoggedIn(token: string): boolean {
//   //   const key = this._isLoggedInKey + token;
//   //   const storedValue = localStorage.getItem(key);
//   //   return storedValue ? JSON.parse(storedValue) : false;
//   // }

//   // setisLoggedIn(token: string, value: boolean): void {
//   //   const key = this._isLoggedInKey + token;
//   //   localStorage.setItem(key, JSON.stringify(value));
//   // }

//   // logout(token: string) {
//   //   const key = this._isLoggedInKey + token;
//   //   localStorage.removeItem(key);
//   //   localStorage.removeItem('userToken');
//   // }

  
// }
