import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IUserService } from '../../service/IUserService';
import { FakeUserService } from '../../service/fake-user.service';
import { MatTableModule } from '@angular/material/table'
import { IReservationService } from '../../service/IReservationService';
import { FakeReservationService } from '../../service/fake-reservation.service';

@Component({
  selector: 'app-home',
  imports: [
    RouterModule,
    MatTableModule
  ],
  templateUrl: './home.component.html'
})
export class HomeComponent {
  userService: IUserService = inject(FakeUserService);
  reservationService: IReservationService = inject(FakeReservationService);
}
