import { UserTestService } from '../_services/auth/service/userTest.service';
import { Component, OnInit, ViewChild } from '@angular/core';
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

 
  public userResults : Array<any> =[];
  displayedColumns: string[] = ['nomQuiz','result','score', 'date'];

  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private userService: UserTestService,private quizService : QuizService) {
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

    //A remplacer par todoquiz
    /*this.quizService.getAllQuiz().subscribe(data => {
      data.forEach(q => {
        this.userResults.push(q);
        console.log("userResults ", this.userResults);
      })
      this.dataSource = new MatTableDataSource(this.userResults);
    });*/

  

    //this.userResults.push(quizResult);

    //this.dataSource = new MatTableDataSource(this.userResults);
    //console.log("quiz : ",this.userResults);

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
