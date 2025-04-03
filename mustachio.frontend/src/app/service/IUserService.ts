import { User } from "../model/user";
import { UserEditorDto } from "../dto/user-editor-dto";
import { Observable } from "rxjs";

export interface IUserService {
  create(editorDto: UserEditorDto): Observable<void>;
  findAll(): Observable<User[]>;
  findById(id: string): Observable<User>;
  updateById(id: string, editorDto: UserEditorDto): Observable<void>;
  deleteById(id: string): Observable<void>;
}
