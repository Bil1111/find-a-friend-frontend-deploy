import { Component, HostListener } from '@angular/core';
import { ShareService } from '../service/share.service';

@Component({
  selector: 'app-blog-and-news',
  templateUrl: './blog-and-news.component.html',
  styleUrl: './blog-and-news.component.css'
})
export class BlogAndNewsComponent {
constructor(private shareService: ShareService) {}
  isScrollToTopVisible: boolean = false;
  @HostListener('window:scroll',[])
  onWindowScroll() {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    this.isScrollToTopVisible = scrollTop > 300;
  }
    scrollToTop(){window.scrollTo({top:0,});}

    blogData: { title: string, content: string }[] = [];

    ngOnInit(): void {
      this.blogData = this.shareService.getBlogData();
      console.log(this.blogData);
    }
}
