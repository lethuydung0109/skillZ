import { Question } from './question';
import { Competence } from './competence';

export class Quiz {

    idQuiz : number;
    name : string;
    niveau!: number;
    niveauName!: string;
    theme : string;
    seuilValidation :  number;
    duree : number;
    questionsQuiz : Array<Question>;
    quizCompetence !: Competence;
   // stringCompetence : string;
    list_questionId : Array<number>;
    score : number;


    constructor()
    {
        this.idQuiz= 0;
        this.name='';
        //this.niveau=;
        this.theme='';
        this.seuilValidation=0;
        this.duree=0;
        this.questionsQuiz = [];
       // this.stringCompetence='';
      //  this.quizCompetences = [];
      // this.quizCompetences = 0;
        this.stringCompetence='';
        this.score=0;
        this.list_questionId = [];



    }
}
