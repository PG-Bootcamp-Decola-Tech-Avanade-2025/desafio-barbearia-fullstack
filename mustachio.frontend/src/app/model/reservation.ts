export class Reservation {
  public static readonly defaultDurationHours: number = 1;
  id: string;
  userId: string;
  startsAt: Date;
  endsAt: Date;

  constructor(id: string, userId: string, startsAt: Date, endsAt: Date | null = null) {
    this.id = id;
    this.userId = userId;
    this.startsAt = startsAt;
    this.endsAt = endsAt ?? new Date(new Date(startsAt).setHours(startsAt.getHours() + Reservation.defaultDurationHours));
  }

  public static getEmptyReservation(): Reservation {
    return new Reservation("", "", new Date(0), null)
  }
}
