import { User } from "./user.model";

export class Recommendation {
  id?: number;
  
  writerId?: number;

  writerName?: string;
  
  receiverId?: number;

  receiverName?: string;

  content?: string;

  date?: string;

  constructor() {
  }
}
