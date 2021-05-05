import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Badge } from '../models/badge';
import { Observable } from 'rxjs';
import { Poste } from '../models/poste';
import { User } from '../models/user.model';
const API_URL = environment.api_url + '/';

@Injectable({
  providedIn: 'root'
})
export class PosteService {

  public url =environment.api_url;
  constructor(private http : HttpClient) {}

  public createPoste(poste : Poste) : Observable<Poste>
  {
    const routeQuery=this.url+"/createPoste";
    return this.http.post<Poste>(routeQuery,poste);
  }

  public getCandidates(posteId : number) : Observable<Array<User>>
  {
    const routeQuery=this.url+"/getCandidatesForThePost/"+posteId;
    return this.http.get<Array<User>>(routeQuery);
  }

  public getAllPostes() : Observable<Array<Poste>>
  {
    const routeQuery=this.url+"/allPostes";
    return this.http.get<Array<Poste>>(routeQuery);
  }

  public deletePoste(posteId : number) 
  {
    const routeQuery=this.url+"/deletePoste/"+posteId;
    return this.http.delete(routeQuery);
  }

}
