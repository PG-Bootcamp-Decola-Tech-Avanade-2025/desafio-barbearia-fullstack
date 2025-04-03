import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { RegisterReservationComponent } from './components/register-reservation/register-reservation.component';
import { UsersListComponent } from './components/users-list/users-list.component';
import { ReservationsListComponent } from './components/reservations-list/reservations-list.component';

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
    path: "users",
    children: [
      {
        path: "",
        pathMatch: "full",
        component: UsersListComponent
      },
      {
        path: "edit/:id",
        component: RegisterUserComponent
      }
    ]
  },
  {
    path: "reserve",
    pathMatch: "full",
    component: RegisterReservationComponent
  },
  {
    path: "reservations",
    children: [
      {
        path: "",
        pathMatch: "full",
        component: ReservationsListComponent
      },
      {
        path: "edit/:id",
        component: RegisterReservationComponent
      }
    ]
  }
];
