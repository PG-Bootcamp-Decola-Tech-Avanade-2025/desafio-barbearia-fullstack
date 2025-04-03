import { Observable } from "rxjs";
import { ReservationEditorDto } from "../dto/reservation-editor-dto";
import { Reservation } from "../model/reservation";


export interface IReservationService {
  create(editorDto: ReservationEditorDto): Observable<void>;
  findAll(): Observable<Reservation[]>;
  findById(id: string): Observable<Reservation>
  updateById(id: string, editorDto: ReservationEditorDto): Observable<void>
  deleteById(id: string): Observable<void>
}
