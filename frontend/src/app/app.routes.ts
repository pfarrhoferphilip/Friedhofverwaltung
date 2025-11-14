import { Routes } from '@angular/router';
import {SearchComponent} from './search/search.component';
import {PersonComponent} from './person/person.component';
import {AdminComponent} from './admin/admin.component';
import {NotFoundComponent} from './not-found/not-found.component';

export const routes: Routes = [
  {
    title: "Friedhof Altenberg",
    path: "",
    component: SearchComponent
  },
  {
    title: "Friedhof Altenberg",
    path: "person/:personId",
    component: PersonComponent
  },
  {
    title: "Admin Seite",
    path: "admin",
    component: AdminComponent
  },
  {
    title: "Seite nicht gefunden",
    path: "**",
    component: NotFoundComponent
  }
];
