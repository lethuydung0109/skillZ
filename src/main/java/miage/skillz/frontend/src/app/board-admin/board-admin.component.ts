import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PublicContent } from '../models/public-content';
import { CompetenceService } from '../services/competence.service';
import { PublicContentService } from '../services/public-content.service';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {

  nbusers = 0;
  nbParticipants = 0;
  nbConcepteurs = 0;
  nbCompetences = 0;
  content?: string;
  editContent?: string;
  submitted = false;

  publicContent: PublicContent ={
    id: 0,
    content: '',
    date:''
  }


  constructor(private userService: UserService, private competenceService: CompetenceService,
    private publicContentService: PublicContentService, private datePipe: DatePipe) { }

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

    this.publicContentService.getLatestPublicContent().subscribe(
      data => {
        this.content = data;
        console.log(this.content);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
}
savePublicContent(): void {
    
  this.publicContent.date = this.datePipe.transform(new Date(), 'yyyy-MM-dd')!
  this.publicContent.content = this.editContent;
  this.publicContentService.savePublicContent(this.publicContent)
    .subscribe( 
      response => {
      console.log(response);
      this.submitted = true;
    },
    error => {
      console.log(error);
    });

  this.content = this.editContent;
}
}