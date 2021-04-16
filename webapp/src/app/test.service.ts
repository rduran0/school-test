import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor() { }

  getMessage(): Observable<string>{
    return of("Message from service").pipe();
  }
}
