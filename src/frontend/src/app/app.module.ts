import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { FindFComponent } from './find-f/find-f.component';
import { BlogAndNewsComponent } from './blog-and-news/blog-and-news.component';
import { DonateComponent } from './donate/donate.component';
import { FreePeopleComponent } from './free-people/free-people.component';
import { SingINComponent } from './sing-in/sing-in.component';
import { RegestComponent } from './regest/regest.component';
import { AdoptComponent } from './adopt/adopt.component';
import { GifthouseComponent } from './gifthouse/gifthouse.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    FindFComponent,
    BlogAndNewsComponent,
    DonateComponent,
    FreePeopleComponent,
    SingINComponent,
    RegestComponent,
    AdoptComponent,
    GifthouseComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
