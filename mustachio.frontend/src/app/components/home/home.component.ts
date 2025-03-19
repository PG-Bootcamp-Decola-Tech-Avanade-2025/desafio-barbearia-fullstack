import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IUserService } from '../../services/IUserService';
import { FakeUserService } from '../../services/fake-user.service';
import { MatTableModule } from '@angular/material/table'

@Component({
  selector: 'app-home',
  imports: [
    RouterModule,
    MatTableModule
  ],
  templateUrl: './home.component.html'
})
export class HomeComponent {
  userService: IUserService = inject(FakeUserService);
}
