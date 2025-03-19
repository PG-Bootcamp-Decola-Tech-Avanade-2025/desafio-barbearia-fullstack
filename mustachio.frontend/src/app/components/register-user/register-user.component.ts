import { Component, inject } from '@angular/core';
import { UserEditorFormComponent } from "../user-editor-form/user-editor-form.component";
import { UserEditorDto } from '../../model/user-editor-dto';
import { FakeUserService } from '../../services/fake-user.service';
import { IUserService } from '../../services/IUserService';

@Component({
  selector: 'app-register-user',
  imports: [UserEditorFormComponent],
  templateUrl: './register-user.component.html'
})
export class RegisterUserComponent {
  private userService: IUserService = inject(FakeUserService);

  registerUser(userEditorDto: UserEditorDto) {
    this.userService.create(userEditorDto);
  }
}
