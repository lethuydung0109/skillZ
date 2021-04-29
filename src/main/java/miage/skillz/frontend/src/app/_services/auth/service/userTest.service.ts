import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const API_URL = environment.api_url + '/test/';

@Injectable({
  providedIn: 'root'
})
export class UserTestService {
  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getParticipantBoard(): Observable<any> {
    return this.http.get(API_URL + 'participant', { responseType: 'text' });
  }

  getConcepteurBoard(): Observable<any> {
    return this.http.get(API_URL + 'concepteur', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }
}
