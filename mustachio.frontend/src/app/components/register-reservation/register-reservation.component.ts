import { Component, inject } from '@angular/core';
import { IReservationService } from '../../service/IReservationService';
import { FakeReservationService } from '../../service/fake-reservation.service';
import { ReservationEditorDto } from '../../dto/reservation-editor-dto';
import { ReservationEditorFormComponent } from '../reservation-editor-form/reservation-editor-form.component';

@Component({
  selector: 'app-register-reservation',
  imports: [ReservationEditorFormComponent],
  templateUrl: './register-reservation.component.html'
})
export class RegisterReservationComponent {
  private reservationService:IReservationService = inject(FakeReservationService);

  registerReservation(reservationEditorDto: ReservationEditorDto) {
    this.reservationService.create(reservationEditorDto);
  }
}
