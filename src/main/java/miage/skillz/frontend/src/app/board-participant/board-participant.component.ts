import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from '../_services/auth/service/user.service';
import { Quiz } from '../models/quiz';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { QuizService } from '../services/quiz.service';

@Component({
  selector: 'app-board-participant',
  templateUrl: './board-participant.component.html',
  styleUrls: ['./board-participant.component.css']
})
export class BoardParticipantComponent implements OnInit {
  content?: string;

  public userToDoQuiz : Array<Quiz> =[];
  displayedColumns: string[] = ['num','theme','nom', 'niveau', 'competence','duree'];

  dataSource!: MatTableDataSource<Quiz>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private userService: UserService,private quizService : QuizService) {
    this.dataSource = new MatTableDataSource(this.userToDoQuiz);

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

    let listQuiz : Array<Quiz> =[];
    //A remplacer par todoquizz
    this.quizService.getAllQuiz().subscribe(data => {
      data.forEach(q => {  
        listQuiz.push(q);
        console.log("listQuiz ", listQuiz);
      })
      this.dataSource = new MatTableDataSource(listQuiz);
    });
    this.userToDoQuiz=listQuiz;
    console.log("quiz : ",this.userToDoQuiz);

    console.log("datasource : ", this.dataSource.data);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }



}
