import { Quiz } from './quiz';
import { ResponseQuestion } from './response-question';
import {Competence} from "./competence";


export class Question {

    idQuestion : number;
    libelle : string;
    theme : string;
    niveau !:  any;
    qst: string;
    reponsesQuestions : Array<ResponseQuestion> = [];

    idNiveau !:  number;
    niveauName!: string;

    questionCompetences : Array<Competence>;
    stringCompetence : string;

    constructor()
    {
      this.idQuestion=0;
      this.theme='';
      this.libelle='';
      this.idNiveau=0;
      this.questionCompetences =[];
      this.reponsesQuestions =[];
      this.stringCompetence='';
      this.qst='';
    }
}
