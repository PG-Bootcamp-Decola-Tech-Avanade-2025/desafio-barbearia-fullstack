import { Reservation } from "../model/reservation";

export class ReservationEditorDto {
  userId: string;
  startsAt: Date;
  endsAt: Date;

  constructor(userId: string, startsAt: Date, endsAt: Date | null = null) {
    this.userId = userId;
    this.startsAt = startsAt;
    this.endsAt = endsAt ?? new Date(new Date(startsAt).setHours(startsAt.getHours() + Reservation.defaultDurationHours));
  }
}
