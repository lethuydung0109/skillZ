import { Question } from './question';
import { Competence } from './competence';

export class Quiz {

    idQuizz : number;
    name : string;
    niveau: string;
    theme : string;
    pourcentageValidation :  number;
    duree : number;
    questionsQuizz : Array<Question>;
    quizCompetences : Array<Competence>;
    list_questionId : Array<number>;
  //  quizCompetences : number
    stringCompetence : string;
    score : number;


    constructor()
    {
        this.idQuizz= 0;
        this.name='';
        this.niveau='';
        this.theme='';
        this.pourcentageValidation=0;
        this.duree=0;
        this.questionsQuizz = [];
        this.quizCompetences = [];
      // this.quizCompetences = 0;
        this.stringCompetence='';
        this.score=0;
        this.list_questionId = [];



    }
}
