import { Component, OnInit } from '@angular/core';
import { CompetenceService } from '../services/competence.service';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  content?: string;
  nbusers = 0;
  nbParticipants = 0;
  nbConcepteurs = 0;
  nbCompetences = 0;


  constructor(private userService: UserService, private competenceService: CompetenceService) { }

  ngOnInit(): void {
    this.userService.getStatsUser().subscribe(
      data => {
        var jsonData = JSON.parse(data);
        this.nbusers = jsonData.nbUtilisateur;
        this.nbParticipants = jsonData.nbParticipants;
        this.nbConcepteurs = jsonData.nbConcepteurs;
        console.log(data);
        console.log("nbuser = " +  this.nbusers);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

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
