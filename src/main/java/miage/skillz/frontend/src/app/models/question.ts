import { Quiz } from './quiz';
import { ResponseQuestion } from './response-question';

export class Question {

    idQuestion : number;
    name : string;
    poids :  number ;
    niveau :  string;
    competences : Array<string>;
    listQuizz : Array<Quiz>;
    reponses : Array<ResponseQuestion>;

    constructor()
    {
        this.idQuestion=0;
        this.name='';
        this.poids=0;
        this.niveau='';
        this.competences =[];
        this.listQuizz =[];
        this.reponses =[];
    }
}
