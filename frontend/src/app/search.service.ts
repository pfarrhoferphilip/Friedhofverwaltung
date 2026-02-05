import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Person} from './person';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  httpClient: HttpClient = inject(HttpClient);

  private readonly url: string = 'http://localhost:8080/api';

  constructor() { }

  search(searchTerm: string): Observable<Person[]> {
    return this.httpClient.get<Person[]>(this.url + "/persons/name/" + searchTerm)
      .pipe(map(r => {
        return r;
      }))
  }

  getAll(): Observable<Person[]> {
    return this.httpClient.get<Person[]>(this.url + "/persons");
  }

  getById(id: number): Observable<Person> {
    return this.httpClient.get<Person>(`${this.url}/persons/${id}`);
  }

  post(person: Person): Observable<Person> {
    return this.httpClient.post<Person>(`${this.url}/persons`, person);
  }

  update(person: Person): Observable<Person> {
    return this.httpClient.put<Person>(`${this.url}/persons`, person);
  }

  delete(id: number): Observable<number> {
    console.log(`${this.url}/persons/${id}`);
    return this.httpClient.delete<number>(`${this.url}/persons/${id}`);
  }
}
