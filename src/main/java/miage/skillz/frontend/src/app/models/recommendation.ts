import { User } from "./user.model";

export class recommendation {
    id: string;
  
  writer?: User;

  receiver?: User;

  content?: string;

  constructor() {
  }
}
