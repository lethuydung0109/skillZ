export class QuizResult {
    nomQuiz : string;
    result : boolean;
    score : number;
    date !: Date;

    constructor(){
        this.nomQuiz='';
        this.result=false;
        this.score=0;
        this.date;
    }
}
