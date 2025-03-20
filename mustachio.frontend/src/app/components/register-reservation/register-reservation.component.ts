import { Component, inject } from '@angular/core';
import { IReservationService } from '../../service/IReservationService';
import { FakeReservationService } from '../../service/fake-reservation.service';
import { ReservationEditorDto } from '../../dto/reservation-editor-dto';
import { ReservationEditorFormComponent } from '../reservation-editor-form/reservation-editor-form.component';
import { ReservationsListComponent } from "../reservations-list/reservations-list.component";
import { ReservationService } from '../../service/reservation.service';

@Component({
  selector: 'app-register-reservation',
  imports: [
    ReservationEditorFormComponent,
    ReservationsListComponent
  ],
  templateUrl: './register-reservation.component.html'
})
export class RegisterReservationComponent {
  private reservationService:IReservationService = inject(ReservationService);

  registerReservation(reservationEditorDto: ReservationEditorDto) {
    this.reservationService.create(reservationEditorDto).subscribe();
  }
}
