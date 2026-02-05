import {Component, inject, OnInit} from '@angular/core';
import {Grave} from '../grave';
import {GraveService} from '../grave.service';
import {DecimalPipe, NgForOf, NgIf} from '@angular/common';
import {SimpleGrave} from '../simple-grave';
import {Person} from '../person';
import {SearchService} from '../search.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-admin',
  imports: [
    NgForOf,
    FormsModule,
    DecimalPipe,
    NgIf
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{

  graves: SimpleGrave[] | undefined;
  graveService: GraveService = inject(GraveService);
  searchService: SearchService = inject(SearchService);
  selectedPersonId: number = 0;
  persons: Person[] = [];
  selectedGraveId: number = 0;

  ngOnInit(): void {
    this.setGraves();
    this.setPersons();
  }

  setPersons() {
    this.searchService.getAll().subscribe((data: Person[]) => {
      this.persons = data;
    })
  }

  setGraves(): void {
    this.graveService.getAll().subscribe(graves => {
      this.graves = graves;
    })
  }

  // Form-Model
  personForm: Partial<Person> = {
    firstname: '',
    lastname: '',
    housename: '',
    birthyear: undefined,
    date_of_death: undefined,
    age: undefined,
    grave_id: undefined
  };

  // Form-Model
  personUpdateForm: Partial<Person> = {
    id: 0,
    firstname: '',
    lastname: '',
    housename: '',
    birthyear: undefined,
    date_of_death: undefined,
    age: undefined,
    grave_id: undefined
  };

  createPerson() {
    if (!this.personForm.firstname || !this.personForm.lastname || !this.personForm.grave_id) {
      return;
    }

    const payload: Person = {
      id: 0, // Backend generiert ID
      firstname: this.personForm.firstname,
      lastname: this.personForm.lastname,
      housename: this.personForm.housename ?? '',
      birthyear: this.personForm.birthyear ?? 0,
      date_of_death: this.personForm.date_of_death ?? new Date('0000-00-00'),
      age: this.personForm.age ?? -1,
      grave: '',                 // wird serverseitig gesetzt oder später berechnet
      grave_id: this.personForm.grave_id
    };

    this.searchService.post(payload).subscribe(response => {
      console.log(response);
        this.personForm = {
          firstname: '',
          lastname: '',
          housename: '',
          birthyear: undefined,
          date_of_death: undefined,
          age: undefined,
          grave_id: undefined
        };
    })
  }

  graveForm: Partial<Grave> = {
    sector: undefined,
    row: undefined,
    col: undefined,
    col2: undefined,
    special_name: '',
    pos_x: undefined,
    pos_y: undefined
  };

  createGrave() {

    const gravePayload: Grave = {
      id: 0,
      sector: this.graveForm.sector ?? 0,
      row: this.graveForm.row ?? 0,
      col: this.graveForm.col ?? 0,
      col2: this.graveForm.col2 ?? 0,
      special_name: this.graveForm.special_name ?? '',
      pos_x: this.graveForm.pos_x ?? 0,
      pos_y: this.graveForm.pos_y ?? 0,
    };

    this.graveService.post(gravePayload).subscribe(response => {
      console.log(response);
      this.graveForm = {
        sector: undefined,
        row: undefined,
        col: undefined,
        col2: undefined,
        special_name: ''
      }
      this.setGraves();
    });

  }

  onImageClick(event: MouseEvent, img: HTMLImageElement) {
    const x = event.offsetX / img.clientWidth;
    const y = event.offsetY / img.clientHeight;

    this.graveForm.pos_x = Number(x.toFixed(6));
    this.graveForm.pos_y = Number(y.toFixed(6));
  }

  loadPersonData() {
    this.searchService.getById(this.selectedPersonId).subscribe(person => {
      this.personUpdateForm = person;
    })
  }

  updatePerson() {
    if (!this.personUpdateForm.firstname || !this.personUpdateForm.lastname || !this.personUpdateForm.grave_id) {
      return;
    }

    const payload: Person = {
      id: this.selectedPersonId,
      firstname: this.personUpdateForm.firstname,
      lastname: this.personUpdateForm.lastname,
      housename: this.personUpdateForm.housename ?? '',
      birthyear: this.personUpdateForm.birthyear ?? 0,
      date_of_death: this.personUpdateForm.date_of_death ?? new Date('0000-00-00'),
      age: this.personUpdateForm.age ?? -1,
      grave: '',                 // wird serverseitig gesetzt oder später berechnet
      grave_id: this.personUpdateForm.grave_id
    };

    this.searchService.update(payload).subscribe(response => {
      console.log(response);
      alert("Person erfolgreich aktualisiert!");
    })
  }

  deletePerson() {
    console.log(this.selectedPersonId);
    this.searchService.delete(this.selectedPersonId).subscribe(response => {
      this.setPersons();
      this.personUpdateForm = {
        id: 0,
        firstname: '',
        lastname: '',
        housename: '',
        birthyear: undefined,
        date_of_death: undefined,
        age: undefined,
        grave_id: undefined
      }
      alert("Person erfolgreich gelöscht!");
    })
  }

  graveUpdateForm: Partial<Grave> = {
    sector: undefined,
    row: undefined,
    col: undefined,
    col2: undefined,
    special_name: '',
    pos_x: undefined,
    pos_y: undefined
  };

  onImageClickUpdate(event: MouseEvent, img: HTMLImageElement) {
    const x = event.offsetX / img.clientWidth;
    const y = event.offsetY / img.clientHeight;

    this.graveUpdateForm.pos_x = Number(x.toFixed(6));
    this.graveUpdateForm.pos_y = Number(y.toFixed(6));
  }

  loadGraveData() {
    this.graveService.getWholeById(this.selectedGraveId).subscribe(grave => {
      console.log(grave);
      this.graveUpdateForm = grave;
    })
  }

  deleteGrave() {
    this.graveService.delete(this.selectedGraveId).subscribe(response => {
      console.log(response);
      alert("Grab erfolgreich gelöscht!");
      this.setGraves();
    })
  }

  updateGrave() {

    const gravePayload: Grave = {
      id: this.selectedGraveId,
      sector: this.graveUpdateForm.sector ?? 0,
      row: this.graveUpdateForm.row ?? 0,
      col: this.graveUpdateForm.col ?? 0,
      col2: this.graveUpdateForm.col2 ?? 0,
      special_name: this.graveUpdateForm.special_name ?? '',
      pos_x: this.graveUpdateForm.pos_x ?? 0,
      pos_y: this.graveUpdateForm.pos_y ?? 0,
    };

    this.graveService.update(gravePayload).subscribe(response => {
      console.log(response);
      this.setGraves();
      alert("Grab erfolgreich aktualisiert!");
    })
  }

}
