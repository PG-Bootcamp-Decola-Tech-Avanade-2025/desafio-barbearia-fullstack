import { HttpClient } from '@angular/common/http';
import { EventEmitter, inject, Injectable } from '@angular/core';
import { catchError, Observable, of, Subject } from 'rxjs';
import { IUserService } from './IUserService';
import { User } from '../model/user';
import { UserEditorDto } from '../dto/user-editor-dto';

@Injectable({
  providedIn: 'root',
})
export class UserService implements IUserService {
  httpClient: HttpClient = inject(HttpClient);
  basePath: string = 'http://localhost:8080';
  resourcePath: string = '/users';

  create(editorDto: UserEditorDto): Observable<void> {
    return this.httpClient.post<void>(
      this.basePath.concat(this.resourcePath),
      editorDto
    );
  }

  findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(
      this.basePath.concat(this.resourcePath)
    );
  }

  findById(id: string): Observable<User> {
    return this.httpClient
      .get<User>(this.basePath.concat(this.resourcePath, '/', id))
      .pipe(catchError(() => of(User.getEmptyUser())));
  }

  updateById(id: string, editorDto: UserEditorDto): Observable<void> {
    return this.httpClient.put<void>(
      this.basePath.concat(this.resourcePath, '/', id),
      editorDto
    );
  }

  deleteById(id: string): Observable<void> {
    return this.httpClient.delete<void>(
      this.basePath.concat(this.resourcePath, '/', id)
    );
  }
}
