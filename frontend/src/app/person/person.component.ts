import {Component, inject, Input, OnInit} from '@angular/core';
import {Person} from '../person';
import {SearchService} from '../search.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {routes} from '../app.routes';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-person',
  imports: [
    DatePipe,
    RouterLink
  ],
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent implements OnInit{

  personId!: number;
  person: Person | undefined;
  searchService: SearchService = inject(SearchService);
  route: ActivatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    this.personId = this.route.snapshot.params['personId'];
    this.searchService.getById(this.personId).subscribe(person => {

      this.person = person;
    })
  }
}
