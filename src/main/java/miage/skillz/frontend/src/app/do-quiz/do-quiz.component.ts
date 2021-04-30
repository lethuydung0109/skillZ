import { Component, OnInit, ViewChild } from '@angular/core';
import { QuizService } from '../services/quiz.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Quiz } from '../models/quiz';
import { Question } from '../models/question';
import { ResponseQuestion } from '../models/response-question';
import { QuestionService } from '../services/question.service';
import { InfoModalComponent } from '../info-modal/info-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CountdownComponent, CountdownEvent } from 'ngx-countdown';
import { Badge } from '../models/badge';
import { BadgeService } from '../services/badge.service';
import Utils from '../utils';


@Component({
  selector: 'app-do-quiz',
  templateUrl: './do-quiz.component.html',
  styleUrls: ['./do-quiz.component.scss']
})
export class DoQuizComponent implements OnInit {

  quizId : number;
  badgeCompetenceId!:number;
  currentQuiz :  Quiz;
  questions : Array<Question> =[];
  correctResponsesSelected : Array<ResponseQuestion>=[];
  quizCorrectResponses : Array<ResponseQuestion>=[];
  isStart : boolean;
  userResponse: Map<number,Array<ResponseQuestion>> =new Map();
  quizScore : number;
  quizTime !: number;
  nbCorrectResponses:number=0;
  nbCorrectSelectedResponses:number=0;

  @ViewChild('chrono', { static: false }) chrono!: CountdownComponent;
  
  constructor(private actRoute: ActivatedRoute, private quizService : QuizService, private modalService: NgbModal,
              private badgeService : BadgeService, private questionService : QuestionService) { 
    this.quizId = this.actRoute.snapshot.params.id;
    this.currentQuiz= new Quiz();
    this.isStart=false;
    this.quizScore =0;
  }

  ngOnInit(): void {

    //this.quizTime=10;
    this.quizService.getQuiz(this.quizId).subscribe(data => {
      this.currentQuiz=data;
      this.currentQuiz.niveauName=Utils.toStringNiveau(data.niveau);
      this.currentQuiz.idNiveau=data.niveau.niveauId;
      this.quizTime=data.duree;
      this.badgeCompetenceId=data.quizCompetence.id;
    })

    this.quizService.getQuizQuestions(this.quizId).subscribe(data => {
        this.questions=data;
    })
  }

  startQuiz() { 
    this.isStart=true;
    this.chrono.resume(); 
  }

  isResponseChecked(response : ResponseQuestion, qId : number)
  {
    //création de la liste des réponses sélectionnées par le user pour chaque question
    if(response.isSelected)
    {
      if(this.userResponse.has(qId)) this.userResponse.get(qId)?.push(response)    
      else  this.userResponse.set(qId, new Array(response));
    }    
  }

  async validQuizResponse(){
    console.log("User responses : ", this.userResponse);
    let poids=100/this.questions.length;
    //Pour chaque question 
    for (let entry of this.userResponse.entries()) {    
      //Vérifier que les questions sélectionnées sont les bonnes réponses
      //console.log("entry : ", entry[0])
      //console.log("entry[]: ", entry[1].length)
      let tab: Array<ResponseQuestion>=[];

      this.quizCorrectResponses= await this.questionService.getQuestionCorrectResponse2(entry[0]).then((data)=>{return data});
      console.log("this.quizCorrectResponses : ", this.quizCorrectResponses, this.quizCorrectResponses.length)


      this.correctResponsesSelected= await this.questionService.getQuestionCorrectResponse2(entry[0]).then((data)=>{
                                            console.log(" result promise: ", data)
                                            entry[1].forEach(selectedResponse  => { // on parcourt la liste des réponses sélectionnées pour la question
                                              data.forEach( correctResponse => { // on parcourt la liste des réponses correctes de la question
                                                if(selectedResponse.idReponse == correctResponse.idReponse)  {
                                                  //this.nbCorrectResponses=data.length;
                                                  tab.push(selectedResponse);
                                                  //this.nbCorrectSelectedResponses=this.nbCorrectSelectedResponses+this.correctResponsesSelected.length;            
                                                }
                                              })                            
                                            }); 
                                            return tab;
                                          });  

      this.quizScore=this.quizScore+(poids/entry[1].length)*this.quizCorrectResponses.length;
      console.log("this.correctResponsesSelected : ", this.correctResponsesSelected,"this.quizCorrectResponses : ",this.quizCorrectResponses)
    }  
    //console.log("this.correctResponsesSelected : ", this.correctResponsesSelected, this.correctResponsesSelected.length) 
    console.log("poids question : ", poids);
    //this.quizScore=this.correctResponsesSelected.length*(100/this.questions.length);
   
    //Création des bagdes
    console.log("score",this.quizScore)
   
    if(this.quizScore >= this.currentQuiz.seuilValidation) 
    {
      let badge : Badge = new Badge();
      badge.competenceId=this.currentQuiz.quizCompetence.id;
      badge.niveauId=this.currentQuiz.idNiveau;
      console.log("badge ", badge)
      this.badgeService.createBadge(badge).subscribe(data=> console.log("badge return",data));
      this.openValidationModal("Félicitaions !Vous avez réussi le test avec un score de : "+this.quizScore);
    }      
    else this.openValidationModal("Dommage ! Vous avez raté le test. Votre score est de "+this.quizScore);
  }

  endQuiz(e: CountdownEvent) {
    if (e.action == 'done') {
      this.isStart=false;
      this.validQuizResponse();
    }
  }

  endQuizBeforeEndTime(c: CountdownComponent) {
    console.log(c)
    this.isStart=false;
    c.stop;
    this.validQuizResponse();     
  }

  public openValidationModal(message:string) : void {
    const modalRef = this.modalService.open(InfoModalComponent);
    modalRef.componentInstance.message = message;
    modalRef.componentInstance.url = "/participant/"+this.currentQuiz.quizCompetence.id;
  }
}