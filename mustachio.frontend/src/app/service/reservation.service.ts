import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IReservationService } from './IReservationService';
import { Observable } from 'rxjs';
import { ReservationEditorDto } from '../dto/reservation-editor-dto';
import { Reservation } from '../model/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService implements IReservationService {
  httpClient: HttpClient = inject(HttpClient);
  resourcePath: string = "/reservations";

  create(reservationEditorDto: ReservationEditorDto): Observable<void> {
    return this.httpClient.post<void>(
      'http://localhost:8080'.concat(this.resourcePath),
      reservationEditorDto
    );
  }

  findAll(): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(
      'http://localhost:8080'.concat(this.resourcePath)
    );
  }

}
