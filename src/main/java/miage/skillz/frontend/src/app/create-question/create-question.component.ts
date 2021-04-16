import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.component.html',
  styleUrls: ['./create-question.component.scss']
})
export class CreateQuestionComponent implements OnInit {

  public description : string = '';
  public theme : string ='';
  public question : string ='';
  public niveau : string ='';
  public competence : string ='';

  constructor() {

   }

  ngOnInit(): void {
  }

}
