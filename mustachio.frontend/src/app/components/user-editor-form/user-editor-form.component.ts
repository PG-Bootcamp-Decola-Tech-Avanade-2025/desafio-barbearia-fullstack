import { Component, inject, input, InputSignal, OnInit, output, OutputEmitterRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card'
import { UserEditorDto } from '../../dto/user-editor-dto';
import { User } from '../../model/user';

@Component({
  selector: 'app-user-editor-form',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
  ],
  templateUrl: './user-editor-form.component.html',
})
export class UserEditorFormComponent implements OnInit {
  formBuilder: FormBuilder = inject(FormBuilder);

  baseUserData: InputSignal<UserEditorDto> = input<UserEditorDto>(User.getEmptyUser());

  submitValid: OutputEmitterRef<UserEditorDto> = output<UserEditorDto>();

  userEditorForm: FormGroup = this.formBuilder.group({});

  dataLoaded: boolean = false;

  ngOnInit(): void {
    this.userEditorForm = this.formBuilder.group({
      username: [this.baseUserData().username, Validators.required],
      password: [this.baseUserData().password, Validators.required],
    });

    this.dataLoaded = true;
  }

  onSubmit() {
    if (!this.userEditorForm?.valid) {
      return;
    }
    let userEditorDto: UserEditorDto = this.userEditorForm.getRawValue();
    this.submitValid.emit(userEditorDto);
  }
}
