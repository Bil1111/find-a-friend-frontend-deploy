import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShareService {
  private storageKey = 'blog_';
  private blogsCache: { title: string, content: string }[] = [];

  constructor() {
    this.loadBlogs();
  }

  private loadBlogs() {
    this.blogsCache = [];
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
      if (key && key.startsWith(this.storageKey)) {
        const blog = JSON.parse(localStorage.getItem(key) || '{}');
        this.blogsCache.push(blog);
      }
    }
  }

  setBlogData(title: string, content: string) {
    if (!title.trim() || !content.trim()) {
      console.log('Не можна додавати блог з порожніми полями');
      return;
    }

    const timestamp = new Date().getTime(); // Генеруємо унікальний ідентифікатор
    const newBlog = { title, content };

    // Зберігаємо блог під окремим ключем
    localStorage.setItem(`${this.storageKey}${timestamp}`, JSON.stringify(newBlog));

    // Оновлюємо локальну копію даних
    this.blogsCache.push(newBlog);

    console.log('Блог додано:', newBlog);
  }

  getBlogData() {
    // Перевіряємо, чи змінилося щось у localStorage
    this.loadBlogs();
    return this.blogsCache;
  }

}