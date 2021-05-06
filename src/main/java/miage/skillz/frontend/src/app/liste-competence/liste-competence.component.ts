import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CompetenceService} from "../services/competence.service";
import {Competence} from "../models/competence";
import { TokenStorageService } from '../_services/auth/service/token-storage.service';


@Component({
  selector: 'app-liste-competence',
  templateUrl: './liste-competence.component.html',
  styleUrls: ['./liste-competence.component.scss']
})
export class ListeCompetenceComponent implements OnInit {
  private roles: string[] = [];
  isAdmin = false;

  list_competence: Array<Competence> =[];


  constructor(private tokenStorageService: TokenStorageService, private router: Router, private competenceService: CompetenceService) { }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();
    this.roles = user.roles;
    console.log(this.roles[0]);
    this.isAdmin = this.roles.includes('ROLE_ADMIN');

    this.loadData();
  }


  loadData() : void {
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p =>
      {
        this.competenceService.getCompetenceById(p.idPere).subscribe(comp_pere => {
          p.nom_competence_pere=comp_pere.nom_competence;
        });
        listComptence.push(p);
      })
    });
    this.list_competence=listComptence;
  }

  updateCompetence(competence: Competence) {
//    this.router.navigate(['modifier-competence',competence]);

  }

  deleteCompetence(competence: Competence) {
    this.competenceService.deleteCompetence(competence.id).subscribe(
      data=> {
        console.log(data);
      },
      error => console.log(error)
    );
    this.list_competence.splice(this.list_competence.indexOf(competence),1);
  }



}
