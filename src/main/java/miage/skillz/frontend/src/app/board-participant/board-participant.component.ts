import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from '../_services/auth/service/user.service';
import { Quiz } from '../models/quiz';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { QuizService } from '../services/quiz.service';
import { QuizResult } from '../models/quiz-result';

@Component({
  selector: 'app-board-participant',
  templateUrl: './board-participant.component.html',
  styleUrls: ['./board-participant.component.css']
})
export class BoardParticipantComponent implements OnInit {
  content?: string;

  public userResults : Array<QuizResult> =[];
  displayedColumns: string[] = ['nomQuiz','result','score', 'date'];

  dataSource!: MatTableDataSource<QuizResult>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private userService: UserService,private quizService : QuizService) {
    this.dataSource = new MatTableDataSource(this.userResults);

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

    //A remplacer par todoquizz
    /*this.quizService.getAllQuiz().subscribe(data => {
      data.forEach(q => {  
        this.userResults.push(q);
        console.log("userResults ", this.userResults);
      })
      this.dataSource = new MatTableDataSource(this.userResults);
    });*/

    let quizResult : QuizResult = new QuizResult();
    quizResult.nomQuiz="Les bases en Java";
    quizResult.result=true;
    quizResult.score= 80;
    quizResult.date=new Date();
    
    this.userResults.push(quizResult);
   
    this.dataSource = new MatTableDataSource(this.userResults);
    console.log("quiz : ",this.userResults);

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
