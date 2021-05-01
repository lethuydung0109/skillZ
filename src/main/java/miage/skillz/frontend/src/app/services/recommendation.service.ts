import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recommendation } from '../models/recommendation';

const API_URL = environment.api_url + '/recommendation';

const httpOptions = {
  headers: new HttpHeaders(
    {
      'Content-Type': 'application/json'
    })
};

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Recommendation[]> {
    return this.http.get<Recommendation[]>(API_URL);
  }

  get(id: any): Observable<Recommendation[]> {
    return this.http.get<Recommendation[]>(API_URL + "/" + id);
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

  findByUsername(username: any): Observable<Recommendation[]> {
    return this.http.get<Recommendation[]>(API_URL + '/name/' + username);
  }


}
