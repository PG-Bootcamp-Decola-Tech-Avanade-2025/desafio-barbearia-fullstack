import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IReservationService } from './IReservationService';
import { catchError, Observable, of } from 'rxjs';
import { ReservationEditorDto } from '../dto/reservation-editor-dto';
import { Reservation } from '../model/reservation';

@Injectable({
  providedIn: 'root',
})
export class ReservationService implements IReservationService {
  httpClient: HttpClient = inject(HttpClient);
  basePath: string = 'http://localhost:8080';
  resourcePath: string = this.basePath.concat('/reservations');

  create(reservationEditorDto: ReservationEditorDto): Observable<void> {
    return this.httpClient.post<void>(
      this.resourcePath,
      reservationEditorDto
    );
  }

  findAll(): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(
      this.resourcePath
    );
  }

  findById(id: string): Observable<Reservation> {
    return this.httpClient.get<Reservation>(
      this.resourcePath.concat('/', id)
    )
    .pipe(catchError(() => of(Reservation.getEmptyReservation())))
  }

  updateById(id: string, editorDto: ReservationEditorDto): Observable<void> {
    return this.httpClient.put<void>(
      this.resourcePath.concat('/', id),
      editorDto
    );
  }

  deleteById(id: string): Observable<void> {
    return this.httpClient.delete<void>(
      this.resourcePath.concat('/', id)
    );
  }
}
