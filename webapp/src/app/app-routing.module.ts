import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorPage } from './error-page/error-page.component';
import { StudentPage } from './student-page/student-page.component';
import { WelcomePage } from './welcome-page/welcome-page.component';

const routes: Routes = [
  { path: "", component: WelcomePage },
  { path: "student", component: StudentPage },
  { path: "error", component: ErrorPage }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
