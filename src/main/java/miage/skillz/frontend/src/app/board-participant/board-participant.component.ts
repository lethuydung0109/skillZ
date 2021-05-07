import { UserTestService } from '../_services/auth/service/userTest.service';
import { Component, OnInit } from '@angular/core';
import { Badge } from '../models/badge';
import { BadgeService } from '../services/badge.service';
import { CompetenceService } from '../services/competence.service';
import Utils from '../utils';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-board-participant',
  templateUrl: './board-participant.component.html',
  styleUrls: ['./board-participant.component.css']
})
export class BoardParticipantComponent implements OnInit {
  content?: string;

  public userBadges : Array<Badge> =[];
  badgeCompetenceId : number;

  constructor(private actRoute: ActivatedRoute,private userService: UserTestService,private badgeService : BadgeService,
                 private competenceService: CompetenceService) {

    this.badgeCompetenceId = this.actRoute.snapshot.params.idBagde;
  }

  ngOnInit(): void {
    this.userService.getParticipantBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

    //A remplacer par todoquiz
    this.badgeService.getCurrentUserBadges().subscribe(data => {
      data.forEach(b => {
        if(this.badgeCompetenceId != undefined){
          b.competenceName=b.competence?.nom_competence;
        }
        else{
          b.competenceName=b.competence?.nom_competence;
        }
        b.niveauName=Utils.toStringNiveau(b.niveau.niveauId)
        this.userBadges.push(b);
        console.log("userResults ", this.userBadges);
      })
    });

    // this.badgeService.getNumberOfBadgeByUserId().subscribe(data => {
    //   data.forEach(b => {
    //     if(this.badgeCompetenceId != undefined){
    //       b.competenceName=b.competence?.nom_competence;
    //     }
    //     else{
    //       b.competenceName=b.competence?.nom_competence;
    //     }
    //     b.niveauName=Utils.toStringNiveau(b.niveau.niveauId)
    //     this.userBadges.push(b);
    //     console.log("userResults ", this.userBadges);
    //   })
    // });
  }

}
