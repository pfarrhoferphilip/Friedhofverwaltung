import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  httpClient: HttpClient = inject(HttpClient);

  private readonly url: string = 'http://localhost:8080/api';

  login(pw: string): Observable<boolean> {
    return this.httpClient.get<boolean>(this.url + '/login/' + pw);
  }

  constructor() { }
}
