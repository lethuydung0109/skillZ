export class Question {

  idQuestion: number;
  name: string;
  niveau: string;
  description: string;
  theme: string;
  competence: string;
  qst: string;

  constructor() {
    this.idQuestion = 0;
    this.name = '';
    this.niveau = '';
    this.description = '';
    this.theme = '';
    this.competence='';
    this.qst='';
  }

}
