import { Component, ViewChild, ElementRef } from '@angular/core';
import { ShareService } from '@service/share.service';


@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrl: './blog.component.css'
})
export class BlogComponent {
  constructor(private shareService: ShareService) {}
  
  @ViewChild('editor') editor!: ElementRef;
  @ViewChild('fileInput') fileInput!: ElementRef;

  blogTitle: string = '';
  blogContent: string = '';

  formatText(command: string) {
    document.execCommand(command, false);
  }
 
  saveBlogData() {
    const content = (document.querySelector('.text-editor') as HTMLElement).innerHTML;
    this.shareService.setBlogData(this.blogTitle, content);
    console.log("Збережено", this.blogTitle, this.editor.nativeElement.innerHTML);


  this.blogTitle = '';
  this.editor.nativeElement.innerHTML = '';
  }

  // blog: { title: string, content: string }[] = [];
  
  // getBlog(){
  //   this.blog = this.shareService.getBlogData();
  //   console.log(this.blog);
  // }
  
 
}
