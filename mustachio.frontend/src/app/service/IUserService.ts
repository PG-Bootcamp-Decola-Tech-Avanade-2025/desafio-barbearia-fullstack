import { User } from "../model/user";
import { UserEditorDto } from "../dto/user-editor-dto";
import { Observable } from "rxjs";

export interface IUserService {
  create(userEditorDto: UserEditorDto): Observable<void>;
  findAll(): Observable<User[]>;
}
