import { Component, inject, Signal, viewChild } from '@angular/core';
import { IUserService } from '../../service/IUserService';
import { FakeUserService } from '../../service/fake-user.service';
import { MatTable, MatTableModule } from '@angular/material/table'
import { User } from '../../model/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-users-list',
  imports: [
    MatTableModule
  ],
  templateUrl: './users-list.component.html'
})
export class UsersListComponent {
  userService: IUserService = inject(UserService);
  usersTable: Signal<MatTable<User> | undefined> = viewChild(MatTable<User>);

  ngDoCheck(): void {
    try {
      this.usersTable()?.renderRows();
    } catch (_) {}
  }
}
