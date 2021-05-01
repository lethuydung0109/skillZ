import { Component, OnInit, ViewChild } from '@angular/core';
import { Question } from '../models/question';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { QuestionService } from '../services/question.service';
import { Competence } from '../models/competence';
import Utils from '../utils';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
export class QuestionsComponent implements OnInit {

  public listQuestions : Array<Question> =[];
  displayedColumns: string[] = ['num','theme','nom','niveau','competence'];

  dataSource!: MatTableDataSource<Question>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private questionService : QuestionService) {
    this.dataSource = new MatTableDataSource(this.listQuestions);
  }

  ngOnInit(): void {
    let listQ : Array<Question> =[];
    this.questionService.getAllQuestions().subscribe(data => {
      data.forEach(q => {     
        q.stringCompetence=Utils.quizCompetenceToString(q.questionCompetences);
        console.log(" stringCompetence :", q.stringCompetence);  
        q.niveauName=Utils.toStringNiveau(q.niveau);
        console.log(" niveauName :", q.niveauName);   
        listQ.push(q);
      })
      this.dataSource = new MatTableDataSource(this.listQuestions);
    });
    this.listQuestions=listQ;
    console.log("quiz : ",this.listQuestions);

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
