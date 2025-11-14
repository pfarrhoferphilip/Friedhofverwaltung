import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Person} from './person';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  httpClient: HttpClient = inject(HttpClient);

  private readonly url: string = 'api.philip-pfarrhofer.at/api';

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
    return this.httpClient.get<Person>(`${this.url}/persons/${id}`)
  }
}
