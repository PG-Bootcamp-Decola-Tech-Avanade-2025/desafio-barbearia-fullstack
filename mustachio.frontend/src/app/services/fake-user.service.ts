import { Injectable } from '@angular/core';
import { IUserService } from './IUserService';
import { UserEditorDto } from '../model/user-editor-dto';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class FakeUserService implements IUserService{
  users: User[] = [];

  create(userEditorDto: UserEditorDto): void {
    let newUser: User = {...userEditorDto, id: this.users.length.toString()};
    this.users.push(newUser);
  }

  findAll(): User[] {
    return this.users;
  }
}
