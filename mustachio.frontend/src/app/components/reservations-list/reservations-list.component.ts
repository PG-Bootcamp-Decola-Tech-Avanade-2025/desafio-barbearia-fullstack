import { Component, DoCheck, inject, Signal, viewChild } from '@angular/core';
import { MatTable, MatTableModule } from '@angular/material/table';
import { IReservationService } from '../../service/IReservationService';
import { FakeReservationService } from '../../service/fake-reservation.service';
import { Reservation } from '../../model/reservation';

@Component({
  selector: 'app-reservations-list',
  imports: [
    MatTableModule
  ],
  templateUrl: './reservations-list.component.html'
})
export class ReservationsListComponent implements DoCheck{
  reservationService: IReservationService = inject(FakeReservationService);
  reservationsTable: Signal<MatTable<Reservation> | undefined> = viewChild(MatTable<Reservation>);

  ngDoCheck(): void {
    try {
      this.reservationsTable()?.renderRows();
    } catch (_) {}
  }
}
