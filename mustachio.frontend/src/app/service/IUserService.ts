import { User } from "../model/user";
import { UserEditorDto } from "../dto/user-editor-dto";

export interface IUserService {
  create(userEditorDto: UserEditorDto): void;
  findAll(): User[];
}
