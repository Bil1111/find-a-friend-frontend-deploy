import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-blog-and-news',
  templateUrl: './blog-and-news.component.html',
  styleUrl: './blog-and-news.component.css'
})
export class BlogAndNewsComponent {

  isScrollToTopVisible: boolean = false;
  @HostListener('window:scroll',[])
  onWindowScroll() {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    this.isScrollToTopVisible = scrollTop > 300;
  }
    scrollToTop(){window.scrollTo({top:0,});}
}
