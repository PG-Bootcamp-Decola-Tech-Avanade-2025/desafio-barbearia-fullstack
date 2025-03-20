import { Injectable } from '@angular/core';
import { IReservationService } from './IReservationService';
import { ReservationEditorDto } from '../dto/reservation-editor-dto';
import { Reservation } from '../model/reservation';
import { from, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FakeReservationService implements IReservationService {
  reservations: Reservation[] = [];

  create(reservationEditorDto: ReservationEditorDto): Observable<void> {
    let newReservation: Reservation = {...reservationEditorDto, id: this.reservations.length.toString()};
    this.reservations.push(newReservation);
    return new Observable<void>();
  }

  findAll(): Observable<Reservation[]> {
    return from([this.reservations]);
  }
}
