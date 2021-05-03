import { Component, OnInit } from '@angular/core';
import { CompetenceService } from '../services/competence.service';
import { UserService } from '../services/user.service';
import { UserTestService } from '../_services/auth/service/userTest.service';

@Component({
  selector: 'app-board-concepteur',
  templateUrl: './board-concepteur.component.html',
  styleUrls: ['./board-concepteur.component.css']
})
export class BoardConcepteurComponent implements OnInit {
  content?: string;
  nbCompetences = 0;

  constructor(private competenceService: CompetenceService) { }

  ngOnInit(): void {
    this.competenceService.getStatsCompetence().subscribe(
      data => {       
        this.nbCompetences = data;
        console.log(data);
        console.log(this.nbCompetences);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
