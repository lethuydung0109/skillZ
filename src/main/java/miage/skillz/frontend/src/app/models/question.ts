import { Quiz } from './quiz';
import { ResponseQuestion } from './response-question';
import {Competence} from "./competence";


export class Question {

    idQuestion : number;
    libelle : string;
    theme : string;
    niveau !:  any;
    poids :  number ;
    qst: string;
    reponsesQuestions : Array<ResponseQuestion> = [];

    idNiveau !:  number;
    niveauName!: string;

    questionCompetences : Array<Competence>;

   // listQuizz : Array<Quiz>;

    stringCompetence : string;



    constructor()
    {
        this.idQuestion=0;
        this.theme='';
        this.libelle='';
        this.poids=0;
        this.idNiveau=0;
        this.questionCompetences =[];
      //  this.listQuiz =[];
        this.reponsesQuestions =[];
        this.stringCompetence='';
        this.qst='';


    }
}
