import { Component, OnInit } from '@angular/core';
import { UserTestService } from '../_services/auth/service/userTest.service';

@Component({
  selector: 'app-board-participant',
  templateUrl: './board-participant.component.html',
  styleUrls: ['./board-participant.component.css']
})
export class BoardParticipantComponent implements OnInit {
  content?: string;

  constructor(private userService: UserTestService) { }

  ngOnInit(): void {
    this.userService.getParticipantBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
