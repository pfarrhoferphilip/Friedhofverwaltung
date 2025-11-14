import {Component, inject, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {SearchService} from '../search.service';
import {SearchResultComponent} from '../search-result/search-result.component';
import {Person} from '../person';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-search',
  imports: [
    FormsModule,
    SearchResultComponent,
    NgForOf,
    NgIf
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit{

  searchService: SearchService = inject(SearchService);

  searchTerm: string = ''; // Hier wird das Input gespeichert
  results: Person[] | undefined;
  hasSearched: boolean = false;

  onSearch() {

    if (this.searchTerm.trim()) {

      this.searchService.search(this.searchTerm).subscribe(persons => {

        this.results = persons;
        this.hasSearched = true;
      })
    } else {
      this.searchAll();
    }
  }

  searchAll(): void {
    this.searchService.getAll().subscribe(persons => {

      this.results = persons;
      this.hasSearched = true;
    })
  }

  ngOnInit(): void {
    this.searchAll();
  }
}
