import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/auth/service/user.service';

@Component({
  selector: 'app-board-participant',
  templateUrl: './board-participant.component.html',
  styleUrls: ['./board-participant.component.css']
})
export class BoardParticipantComponent implements OnInit {
  content?: string;

  constructor(private userService: UserService) { }

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
