import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { StudentDTO } from "./model/student.dto";
import { StudentResponse } from "./response/student.response";
import { StudentService } from "./service/student.service";

@Component({
  selector: "student-page",
  templateUrl: "student-page.component.html"
})
export class StudentPage implements OnInit {

  public offset: number
  public limit: number

  public name: string
  public lastName: string
  public age: number

  public students: StudentDTO[]

  constructor(private studentService: StudentService) {
    this.offset = 0
    this.limit = 3

    this.name = this.lastName = ""
    this.age = 18

    this.students = []
  }

  public ngOnInit(): void {
    this.getList()
  }

  public getList(): void {
    this.studentService.getStudents(this.offset, this.limit)
      .subscribe(response => this.processListResponse(response), this.handleError)
  }

  public createStudent(): void {
    this.studentService.createStudent({
      name: this.name,
      lastName: this.lastName,
      age: this.age
    })
    .subscribe(response => {
      alert(response ? "Student created" : "Student creation failed")
    },
    this.handleError)
  }

  private processListResponse(response: StudentResponse): void {
    if (response.students.length < 1) {
      alert("No more students to retrieve")
      return
    }

    this.students = response.students
  }

  private handleError(error: HttpErrorResponse): void {
    alert(`${error.status} HTTP Status error received
    Message:
      ${error.error.message}`)
  }
}
