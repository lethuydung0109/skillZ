import { Quiz } from './quiz';
import { Question } from './question';

export class User {
    id?: any;
  username?: string ;
  email?: string | undefined;
  role?: string;
  password?: string;
  myCreatedQuiz?:Array<Quiz>;
  myCreatedQuestions?:Array<Question>;
  scoreForQuiz ?: number;
  niveauForQuiz ?: string;
}
