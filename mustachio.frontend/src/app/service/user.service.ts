import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUserService } from './IUserService';
import { User } from '../model/user';
import { UserEditorDto } from '../dto/user-editor-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService implements IUserService{
  httpClient: HttpClient = inject(HttpClient);
  resourcePath: string = "/users";

  create(userEditorDto: UserEditorDto): Observable<void> {
    return this.httpClient.post<void>(
      'http://localhost:8080'.concat(this.resourcePath),
      userEditorDto
    );
  }

  findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(
      'http://localhost:8080'.concat(this.resourcePath)
    );
  }
}
