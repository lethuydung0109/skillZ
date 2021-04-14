import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from '../services/quiz.service';
import { HttpClient } from '@angular/common/http';
import { Quiz } from '../models/quiz';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-quizz',
  templateUrl: './quizz.component.html',
  styleUrls: ['./quizz.component.scss']
})
export class QuizzComponent implements OnInit {

  public listQuiz : Array<Quiz> =[];
  displayedColumns: string[] = ['num','theme','nom', 'niveau', 'competence','duree'];

  dataSource!: MatTableDataSource<Quiz>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private actRoute: ActivatedRoute, private quizService : QuizService, private http : HttpClient, private router : Router) { }

  ngOnInit(): void {
    let listQuiz : Array<Quiz> =[];
    this.quizService.getAllQuiz().subscribe(data => {
      data.forEach(q => {      
        listQuiz.push(q);
      })
    });
    this.listQuiz=listQuiz;
    console.log("quiz : ",this.listQuiz);

    this.dataSource = new MatTableDataSource(this.listQuiz);
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
