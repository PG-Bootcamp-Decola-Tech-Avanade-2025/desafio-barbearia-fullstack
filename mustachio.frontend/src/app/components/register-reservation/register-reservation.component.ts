import { Component, inject, Input, OnInit } from '@angular/core';
import { IReservationService } from '../../service/IReservationService';
import { ReservationEditorDto } from '../../dto/reservation-editor-dto';
import { ReservationEditorFormComponent } from '../reservation-editor-form/reservation-editor-form.component';
import { ReservationService } from '../../service/reservation.service';
import { Router } from '@angular/router';
import { Reservation } from '../../model/reservation';
import { firstValueFrom } from 'rxjs';
import { User } from '../../model/user';
import { UserService } from '../../service/user.service';
import { IUserService } from '../../service/IUserService';

@Component({
  selector: 'app-register-reservation',
  imports: [ReservationEditorFormComponent],
  templateUrl: './register-reservation.component.html',
})
export class RegisterReservationComponent implements OnInit {
  private reservationService: IReservationService = inject(ReservationService);
  private userService: IUserService = inject(UserService);
  private router: Router = inject(Router);

  baseReservation: Reservation = Reservation.getEmptyReservation();

  userOptions: User[] = [];

  @Input('id') baseReservationId = '';

  dataLoaded: boolean = false;

  async ngOnInit(): Promise<void> {
    if (!!this.baseReservationId) {
      this.baseReservation = await firstValueFrom(
        this.reservationService.findById(this.baseReservationId)
      );
    }

    this.userOptions = await firstValueFrom(this.userService.findAll());

    this.dataLoaded = true;
  }

  registerReservation(reservationEditorDto: ReservationEditorDto) {
    if (!!this.baseReservationId) {
      this.reservationService
        .updateById(this.baseReservationId, reservationEditorDto)
        .subscribe(() => this.router.navigateByUrl(''));
    } else {
      this.reservationService
        .create(reservationEditorDto)
        .subscribe(() => this.router.navigateByUrl(''));
    }
  }
}
