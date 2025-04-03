import { Component, inject, OnInit, Signal, viewChild } from '@angular/core';
import { IUserService } from '../../service/IUserService';
import { MatTable, MatTableModule } from '@angular/material/table'
import { UserService } from '../../service/user.service';
import { RouterModule } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { User } from '../../model/user';

@Component({
  selector: 'app-users-list',
  imports: [
    MatTableModule,
    RouterModule
  ],
  templateUrl: './users-list.component.html'
})
export class UsersListComponent implements OnInit {
  userService: IUserService = inject(UserService);
  usersTable: Signal<MatTable<User> | undefined> = viewChild(MatTable<User>);
  userData: User[] = [];

  async ngOnInit(): Promise<void> {
    await this.updateUserData();
  }

  deleteUser(id: string) {
    this.userService.deleteById(id).subscribe(async () => {
      await this.updateUserData();
      this.triggerReRender();
    });
  }

  async updateUserData(): Promise<void> {
    this.userData = await firstValueFrom(this.userService.findAll());
  }

  triggerReRender() {
    try {
      this.usersTable()?.renderRows();
    } catch (_) {}
  }
}
