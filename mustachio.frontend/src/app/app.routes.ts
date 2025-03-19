import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { RegisterReservationComponent } from './components/register-reservation/register-reservation.component';

export const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    component: HomeComponent
  },
  {
    path: "register",
    pathMatch: "full",
    component: RegisterUserComponent
  },
  {
    path: "reserve",
    pathMatch: "full",
    component: RegisterReservationComponent
  }
];
