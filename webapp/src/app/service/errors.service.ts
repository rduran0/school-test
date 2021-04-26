import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HOST, SupportedStatus } from "../constant/http.constant";

@Injectable({
  providedIn: "root"
})
export class ErrorsService {

  constructor(private http: HttpClient) {}

  public testError(status: SupportedStatus): Observable<void> {
    return this.http.post<void>(HOST + "dummy/failure/" + status, {})
  }

  public test400(): Observable<void> {
    return this.http.get<void>(HOST + "dummy/failure/default")
  }
}
