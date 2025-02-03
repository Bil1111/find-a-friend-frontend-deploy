import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-admindashbord',
  templateUrl: './admindashbord.component.html',
  styleUrl: './admindashbord.component.css'
})
export class AdmindashbordComponent {
  showRules: boolean = false;

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      this.showRules = this.router.url === '/admin';
    });
  }
}
