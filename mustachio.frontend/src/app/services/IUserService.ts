import { User } from "../model/user";
import { UserEditorDto } from "../model/user-editor-dto";

export interface IUserService {
  create(userEditorDto: UserEditorDto): void;
  findAll(): User[];
}
