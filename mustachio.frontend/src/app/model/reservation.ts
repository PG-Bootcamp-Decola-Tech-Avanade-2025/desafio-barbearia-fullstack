export class Reservation {
  public static readonly defaultDurationHours: number = 1;
  id: string;
  userId: string;
  startsAt: Date;
  endsAt: Date;

  constructor(id: string, userId: string, startsAt: Date, endsAt: Date) {
    this.id = id;
    this.userId = userId;
    this.startsAt = startsAt;
    this.endsAt = endsAt;
  }
}
