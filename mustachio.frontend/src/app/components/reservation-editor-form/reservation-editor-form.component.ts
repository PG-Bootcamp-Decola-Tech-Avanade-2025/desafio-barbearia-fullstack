import { Component, inject, input, InputSignal, OnInit, output, OutputEmitterRef } from '@angular/core';
import { ReservationEditorDto } from '../../dto/reservation-editor-dto';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { FormsModule } from '@angular/forms'
import { Reservation } from '../../model/reservation';
import { MatSelectModule } from '@angular/material/select';
import { User } from '../../model/user';

@Component({
  selector: 'app-reservation-editor-form',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatDatepickerModule,
    MatTimepickerModule,
    FormsModule,
    MatSelectModule
  ],
  templateUrl: './reservation-editor-form.component.html',
})
export class ReservationEditorFormComponent implements OnInit {
  formBuilder: FormBuilder = inject(FormBuilder);

  baseReservationData: InputSignal<ReservationEditorDto> = input<ReservationEditorDto>(Reservation.getEmptyReservation())

  userOptions: InputSignal<User[]> = input<User[]>([]);

  submitValid: OutputEmitterRef<ReservationEditorDto> = output<ReservationEditorDto>();

  reservationEditorForm: FormGroup = this.formBuilder.group({});

  dataLoaded: boolean = false;

  ngOnInit(): void {
    let baseStartsAt =
      new Date(this.baseReservationData().startsAt).getTime() == new Date(0).getTime()
        ? null
        : new Date(this.baseReservationData().startsAt);

    this.reservationEditorForm = this.formBuilder.group({
      userId: [this.baseReservationData().userId, Validators.required],
      startsAt: [baseStartsAt, Validators.required],
    });

    this.dataLoaded = true;
  }

  onSubmit() {
    if (!this.reservationEditorForm.valid) {
      return;
    }

    let startsAt: Date = new Date(this.reservationEditorForm.getRawValue()['startsAt']);
    let utcOffsetHours = startsAt.getTimezoneOffset() / 60;
    startsAt.setHours(startsAt.getHours() - utcOffsetHours);

    let reservationEditorDto: ReservationEditorDto = new ReservationEditorDto(
      this.reservationEditorForm.getRawValue()['userId'],
      startsAt,
      null
    );

    this.submitValid.emit(reservationEditorDto);
  }
}
