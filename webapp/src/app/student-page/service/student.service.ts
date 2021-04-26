import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HOST } from "src/app/constant/http.constant";
import { StudentRequest } from "../request/student.request";

import { StudentResponse } from "../response/student.response"

@Injectable()
export class StudentService {

  constructor(private http: HttpClient) {}

  public getStudents(offset: number, limit: number): Observable<StudentResponse> {
    return this.http.get<StudentResponse>(HOST + "dummy/student/list?offset=" + offset + "&limit=" + limit)
  }

  public createStudent(request: StudentRequest): Observable<boolean> {
    return this.http.post<boolean>(HOST + "dummy/student", request)
  }
}
