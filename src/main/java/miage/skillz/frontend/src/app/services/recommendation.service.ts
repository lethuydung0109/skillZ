import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { recommendation } from '../models/recommendation';

const API_URL = environment.api_url + '/';

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

  //Create recommendation
  public saverecommendation(recommendation: recommendation): Observable<recommendation> {
    return this.http.post<recommendation>( API_URL + 'createrecommendation/', {
      writer: recommendation.writer,
      receiver: recommendation.receiver,
      content: recommendation.content,
    }, httpOptions);
  }

  //Get all recommendation by receiver
  public getAllrecommendationsByReceiver() : Observable<recommendation[]> {
    return this.http.get<recommendation[]>(API_URL  + 'recommendationById/' + id,httpOptions);
  }


}
