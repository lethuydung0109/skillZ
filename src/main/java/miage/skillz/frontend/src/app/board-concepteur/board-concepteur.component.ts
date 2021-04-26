import { Component, OnInit } from '@angular/core';
import { UserTestService } from '../_services/auth/service/userTest.service';

@Component({
  selector: 'app-board-concepteur',
  templateUrl: './board-concepteur.component.html',
  styleUrls: ['./board-concepteur.component.css']
})
export class BoardConcepteurComponent implements OnInit {
  content?: string;

  constructor(private userService: UserTestService) { }

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
