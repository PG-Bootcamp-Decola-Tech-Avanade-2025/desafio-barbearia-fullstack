import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UsersListComponent } from "../users-list/users-list.component";
import { ReservationsListComponent } from "../reservations-list/reservations-list.component";

@Component({
  selector: 'app-home',
  imports: [
    RouterModule,
    UsersListComponent,
    ReservationsListComponent
],
  templateUrl: './home.component.html'
})
export class HomeComponent {
}
