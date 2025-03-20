import { Injectable } from '@angular/core';
import { IUserService } from './IUserService';
import { UserEditorDto } from '../dto/user-editor-dto';
import { User } from '../model/user';
import { from, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FakeUserService implements IUserService{
  users: User[] = [];

  create(userEditorDto: UserEditorDto): Observable<void> {
    let newUser: User = {...userEditorDto, id: this.users.length.toString()};
    this.users.push(newUser);
    return new Observable<void>();
  }

  findAll(): Observable<User[]> {
    return from([this.users]);
  }
}
