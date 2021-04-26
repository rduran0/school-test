import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { StudentService } from "./service/student.service";
import { StudentPage } from "./student-page.component";

@NgModule({
  declarations: [StudentPage],
  providers: [StudentService],
  imports: [
    HttpClientModule,
    CommonModule,
    FormsModule
  ],
  exports: [StudentPage]
})
export class StudentModule {}
