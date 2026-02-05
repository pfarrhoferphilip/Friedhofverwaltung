import {Component, inject, Input, OnInit} from '@angular/core';
import {Person} from '../person';
import {SearchService} from '../search.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {routes} from '../app.routes';
import {DatePipe, NgIf} from '@angular/common';
import {SimpleGrave} from '../simple-grave';
import {GraveService} from '../grave.service';

@Component({
  selector: 'app-person',
  imports: [
    DatePipe,
    RouterLink,
    NgIf
  ],
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent implements OnInit{

  personId!: number;
  person: Person | undefined;
  grave: SimpleGrave | undefined;
  searchService: SearchService = inject(SearchService);
  graveService: GraveService = inject(GraveService);
  route: ActivatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    this.personId = this.route.snapshot.params['personId'];
    this.searchService.getById(this.personId).subscribe(person => {
      this.person = person;

      this.graveService.getById(this.person.grave_id).subscribe(grave => {
        this.grave = grave;
      })
    })
  }
}
