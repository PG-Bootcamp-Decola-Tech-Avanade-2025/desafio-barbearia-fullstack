import { Injectable } from '@angular/core';
import { IReservationService } from './IReservationService';
import { ReservationEditorDto } from '../dto/reservation-editor-dto';
import { Reservation } from '../model/reservation';

@Injectable({
  providedIn: 'root'
})
export class FakeReservationService implements IReservationService {
  reservations: Reservation[] = [];

  create(reservationEditorDto: ReservationEditorDto): void {
    let newReservation: Reservation = {...reservationEditorDto, id: this.reservations.length.toString()};
    this.reservations.push(newReservation);
  }

  findAll(): Reservation[] {
    return this.reservations;
  }
}
