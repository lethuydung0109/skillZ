import { Question } from './question';

export class ResponseQuestion {

    idReponse : number;
    libelle :  string;
    isCorrect : boolean;  // true si c'est une bonne reponse
    isSelected : boolean; // true si le user a sélectionné cette réponse
    //question : Question;

    constructor()
    {
        this.idReponse=0;
        this.libelle='';
        this.isCorrect=false;  // true si c'est une bonne reponse
        this.isSelected=false; // true si le user a sélectionné cette réponse
        //this.question = new Question();
    }
}
