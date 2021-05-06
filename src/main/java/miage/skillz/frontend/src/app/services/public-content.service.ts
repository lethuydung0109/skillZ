import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from 'src/environments/environment';


const API_URL = environment.api_url ;

@Injectable({
  providedIn: 'root'
})
export class PublicContentService {

  constructor(private http: HttpClient) { }

  //Get public content
  getLatesPublicContent(): Observable<any> {
    return this.http.get(API_URL + '/publicContent', { responseType: 'text' });
  }

  savePublicContent(data: any){
    return this.http.post(API_URL + '/publicContent', data);
  }
}
