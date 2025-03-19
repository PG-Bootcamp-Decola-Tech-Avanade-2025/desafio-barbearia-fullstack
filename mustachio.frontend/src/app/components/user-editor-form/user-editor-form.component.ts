import { Component, inject, output, OutputEmitterRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card'
import { UserEditorDto } from '../../dto/user-editor-dto';

@Component({
  selector: 'app-user-editor-form',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule
  ],
  templateUrl: './user-editor-form.component.html'
})
export class UserEditorFormComponent {
  submitValid: OutputEmitterRef<UserEditorDto> = output<UserEditorDto>();

  formBuilder: FormBuilder = inject(FormBuilder);

  userEditorForm: FormGroup = this.formBuilder.group({
    email: [null, Validators.compose([Validators.required, Validators.email])],
    username: ['', Validators.required],
    password: ['', Validators.required],
  });

  alert(s:string) {alert(s)}

  onSubmit() {
    if (!this.userEditorForm.valid) {
      return;
    }
    let userEditorDto: UserEditorDto = this.userEditorForm.getRawValue();
    this.submitValid.emit(userEditorDto);
  }
}
