import { Component, inject } from '@angular/core';
import { UserEditorFormComponent } from "../user-editor-form/user-editor-form.component";
import { UserEditorDto } from '../../dto/user-editor-dto';
import { FakeUserService } from '../../service/fake-user.service';
import { IUserService } from '../../service/IUserService';
import { UsersListComponent } from "../users-list/users-list.component";
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-register-user',
  imports: [
    UserEditorFormComponent,
    UsersListComponent
  ],
  templateUrl: './register-user.component.html'
})
export class RegisterUserComponent {
  private userService: IUserService = inject(UserService);

  registerUser(userEditorDto: UserEditorDto) {
    this.userService.create(userEditorDto).subscribe();
  }
}
