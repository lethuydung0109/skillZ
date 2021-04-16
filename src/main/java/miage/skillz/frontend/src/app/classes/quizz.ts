export class Quizz {

  idQuizz: number;
  name: string;
  niveau: string;
  theme: string;
  seuil: number;
  duree: string;
  competence: number;
  heure: number;
  minute: number;

  constructor() {
    this.idQuizz = 0;
    this.name = '';
    this.niveau = '';
    this.theme = '';
    this.seuil=0;
    this.duree='';
    this.competence=0;
    this.heure=0;
    this.minute=0;
  }
}
