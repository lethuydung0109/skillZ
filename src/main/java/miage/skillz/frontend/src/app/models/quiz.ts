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
    score : number;

// à enlever
  heure: number;
  minute: number;
  competence: number;

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
        this.score=0;

        // à enlever
      this.heure=0;
      this.minute=0;
      this.competence=0;

    }
}
