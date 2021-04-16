import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

// Main routes
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestService } from './test.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [ TestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
