import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CompetenceService} from "../services/competence.service";
import {Competence} from "../classes/competence";
import {Observable} from "rxjs";


@Component({
  selector: 'app-liste-competence',
  templateUrl: './liste-competence.component.html',
  styleUrls: ['./liste-competence.component.scss']
})
export class ListeCompetenceComponent implements OnInit {


  list_competence: Array<Competence> =[];


  constructor(private router: Router, private competenceService: CompetenceService) { }

  ngOnInit(): void {
    this.loadData();
  }


  loadData() : void {
    let listComptence: Array<Competence>=[];
    this.competenceService.getAllCompetence().subscribe(data => {
      data.forEach(p =>
      {
        this.competenceService.getCompetenceById(p.id_pere).subscribe(comp_pere => {
          p.nom_competence_pere=comp_pere.nom_competence;
        });
        listComptence.push(p);
      })
    });
    this.list_competence=listComptence;
  }

  updateCompetence(id: number) {
    this.router.navigate(['update-competence', id]);
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
