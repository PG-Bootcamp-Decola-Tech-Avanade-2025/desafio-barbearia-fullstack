import { Component, DoCheck, inject, OnInit, Signal, viewChild } from '@angular/core';
import { MatTable, MatTableModule } from '@angular/material/table';
import { IReservationService } from '../../service/IReservationService';
import { ReservationService } from '../../service/reservation.service';
import { Reservation } from '../../model/reservation';
import { firstValueFrom } from 'rxjs';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-reservations-list',
  imports: [
    MatTableModule,
    RouterModule
  ],
  templateUrl: './reservations-list.component.html'
})
export class ReservationsListComponent implements OnInit {
  reservationService: IReservationService = inject(ReservationService);
  reservationsTable: Signal<MatTable<Reservation> | undefined> = viewChild(MatTable<Reservation>);
  reservationData: Reservation[] = [];

  async ngOnInit(): Promise<void> {
    await this.updateReservationData();
  }

  deleteReservation(id: string) {
    this.reservationService.deleteById(id).subscribe(async () => {
      await this.updateReservationData();
      this.triggerReRender();
    });
  }

  async updateReservationData(): Promise<void> {
    this.reservationData = await firstValueFrom(this.reservationService.findAll());
  }

  triggerReRender() {
    try {
      this.reservationsTable()?.renderRows();
    } catch (_) {}
  }
}
