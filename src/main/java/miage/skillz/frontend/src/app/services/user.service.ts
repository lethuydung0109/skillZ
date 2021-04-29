import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';

const API_URL = environment.api_url + '/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  //Get user stats
  getStatsUser(): Observable<any> {
    return this.http.get(API_URL + '/stats', { responseType: 'text' });
  }

  //Get user list
  getAll(): Observable<User[]> {
    return this.http.get<User[]>(API_URL);
  }

  get(id: any): Observable<User> {
    return this.http.get(`${API_URL}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(API_URL, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${API_URL}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${API_URL}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(API_URL);
  }

  findByUsername(username: any): Observable<User[]> {
    return this.http.get<User[]>(`${API_URL}?username=${username}`);
  }
}


