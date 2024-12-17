import { Component, OnInit  } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'FF';
    isHomePage: boolean = false;

    constructor(private router: Router) {}

    ngOnInit() {
      this.router.events.subscribe(() => {
        this.isHomePage = this.router.url === '/';
      });
    }

    visible = false;
    closeMenu(){this.visible = false;}
    Openmemu(){this.visible = true;}
}
