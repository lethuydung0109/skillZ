import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8081/api/';
@Injectable({
  providedIn: 'root'
})
export class CompetenceService {

  constructor(private http: HttpClient) { }

  getStatsCompetence(): Observable<any> {
    return this.http.get(API_URL + 'competenceStats', { responseType: 'text' });
  }
}
