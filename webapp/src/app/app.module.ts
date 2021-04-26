import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentPage } from './student-page/student-page.component';
import { StudentModule } from './student-page/student.module';
import { ErrorModule } from './error-page/error.module';
import { SurpriseMeDirective } from './directive/surprise.directive';

@NgModule({
  declarations: [
    AppComponent,
    SurpriseMeDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    StudentModule,
    ErrorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
