import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Grave} from './grave';
import {map, Observable} from 'rxjs';
import {Person} from './person';
import {SimpleGrave} from './simple-grave';

@Injectable({
  providedIn: 'root'
})
export class GraveService {
  httpClient: HttpClient = inject(HttpClient);

  private readonly url: string = 'http://localhost:8080/api';

  getAll(): Observable<SimpleGrave[]> {
    return this.httpClient.get<SimpleGrave[]>(this.url + "/graves")
      .pipe(map(r => {
        return r;
      }))
  }

  getById(id: number): Observable<SimpleGrave> {
    return this.httpClient.get<SimpleGrave>(`${this.url}/graves/${id}`);
  }

  getWholeById(id: number): Observable<Grave> {
    return this.httpClient.get<Grave>(`${this.url}/graves/whole/${id}`);
  }

  post(grave: Grave): Observable<Grave> {
    return this.httpClient.post<Grave>(`${this.url}/graves/` + sessionStorage.getItem("password"), grave);
  }

  update(grave: Grave): Observable<Grave> {
    return this.httpClient.put<Grave>(`${this.url}/graves/` + sessionStorage.getItem("password"), grave);
  }

  delete(id: number): Observable<number> {
    return this.httpClient.delete<number>(`${this.url}/graves/${id}/` + sessionStorage.getItem("password"));
  }

  constructor() { }
}
