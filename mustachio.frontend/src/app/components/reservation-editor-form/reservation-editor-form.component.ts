import { Component, inject, output, OutputEmitterRef } from '@angular/core';
import { ReservationEditorDto } from '../../dto/reservation-editor-dto';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { FormsModule } from '@angular/forms'

@Component({
  selector: 'app-reservation-editor-form',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatDatepickerModule,
    MatTimepickerModule,
    FormsModule
  ],
  templateUrl: './reservation-editor-form.component.html',
})
export class ReservationEditorFormComponent {
  submitValid: OutputEmitterRef<ReservationEditorDto> = output<ReservationEditorDto>();

  endsAt: Date = new Date();

  formBuilder: FormBuilder = inject(FormBuilder);

  reservationEditorForm: FormGroup = this.formBuilder.group({
    userId: ['', Validators.required],
    startsAt: [null, Validators.required]
  });

  alert(s: string) {
    alert(s);
  }

  onSubmit() {
    if (!this.reservationEditorForm.valid) {
      return;
    }
    let reservationEditorDto: ReservationEditorDto = new ReservationEditorDto(
      this.reservationEditorForm.getRawValue()['userId'],
      this.reservationEditorForm.getRawValue()['startsAt'],
      null
    );

    this.submitValid.emit(reservationEditorDto);
  }
}
