import { Observable } from "rxjs";
import { ReservationEditorDto } from "../dto/reservation-editor-dto";
import { Reservation } from "../model/reservation";


export interface IReservationService {
  create(reservationEditorDto: ReservationEditorDto): Observable<void>;
  findAll(): Observable<Reservation[]>;
}
