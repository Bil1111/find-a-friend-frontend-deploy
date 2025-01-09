import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrl: './about.component.css'
})
export class AboutComponent {
  isScrollToTopVisible: boolean =false;

@HostListener('window:scroll',[])
onWindowScroll() {
  const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
  this.isScrollToTopVisible = scrollTop > 300;
}


  scrollToTop(){
    window.scrollTo({
      top:0,
    });
  }
}
