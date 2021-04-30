import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Badge } from '../models/badge';
import { Observable } from 'rxjs';

const API_URL = environment.api_url + '/';

@Injectable({
  providedIn: 'root'
})
export class BadgeService {

  public url =environment.api_url;
  constructor(private http : HttpClient) {}

  public createBadge(badge : Badge) : Observable<Badge>
  {
    const routeQuery=this.url+"/badge";
    return this.http.post<Badge>(routeQuery,badge);
  }

  public getAllBadgeByUser() : Observable<Array<Badge>>
  {
    const routeQuery=this.url+"/allBadgesByUser";
    return this.http.get<Array<Badge>>(routeQuery);
  }
}
