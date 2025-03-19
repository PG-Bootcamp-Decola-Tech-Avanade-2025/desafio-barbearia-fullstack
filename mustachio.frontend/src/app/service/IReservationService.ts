import { ReservationEditorDto } from "../dto/reservation-editor-dto";
import { Reservation } from "../model/reservation";


export interface IReservationService {
  create(reservationEditorDto: ReservationEditorDto): void;
  findAll(): Reservation[];
}
