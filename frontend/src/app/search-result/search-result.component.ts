import {Component, Input} from '@angular/core';
import {Person} from '../person';
import {NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-search-result',
  imports: [
    NgIf,
    RouterLink
  ],
  templateUrl: './search-result.component.html',
  styleUrl: './search-result.component.css'
})
export class SearchResultComponent {

  @Input() person!: Person;
}
