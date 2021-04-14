import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/auth/service/user.service';

@Component({
  selector: 'app-board-concepteur',
  templateUrl: './board-concepteur.component.html',
  styleUrls: ['./board-concepteur.component.css']
})
export class BoardConcepteurComponent implements OnInit {
  content?: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getConcepteurBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
