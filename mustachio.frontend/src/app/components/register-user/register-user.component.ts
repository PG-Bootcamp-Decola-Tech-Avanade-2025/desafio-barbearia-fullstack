import { Component, inject, Input, OnInit } from '@angular/core';
import { UserEditorFormComponent } from "../user-editor-form/user-editor-form.component";
import { UserEditorDto } from '../../dto/user-editor-dto';
import { IUserService } from '../../service/IUserService';
import { UserService } from '../../service/user.service';
import { User } from '../../model/user';
import { firstValueFrom } from 'rxjs';
import { UsersListComponent } from "../users-list/users-list.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-user',
  imports: [
    UserEditorFormComponent
  ],
  templateUrl: './register-user.component.html',
})
export class RegisterUserComponent implements OnInit{
  private userService: IUserService = inject(UserService);
  private router: Router = inject(Router);

  baseUser: User = User.getEmptyUser();

  @Input("id") baseUserId: string = "";

  dataLoaded: boolean = false;

  async ngOnInit(): Promise<void> {
    if(!!this.baseUserId) {
      this.baseUser = await firstValueFrom(this.userService.findById(this.baseUserId));
    }
    this.dataLoaded = true;
  }

  registerUser(userEditorDto: UserEditorDto) {
    if(!!this.baseUserId) {
      this.userService
        .updateById(this.baseUserId, userEditorDto)
        .subscribe(() => this.router.navigateByUrl(''));
    } else {
      this.userService
        .create(userEditorDto)
        .subscribe(() => this.router.navigateByUrl(''));
    }
  }
}
