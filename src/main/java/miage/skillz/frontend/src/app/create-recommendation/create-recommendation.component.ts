import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Recommendation } from '../models/recommendation';
import { User } from '../models/user.model';
import { RecommendationService } from '../services/recommendation.service';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../_services/auth/service/token-storage.service';

@Component({
  selector: 'app-create-recommendation',
  templateUrl: './create-recommendation.component.html',
  styleUrls: ['./create-recommendation.component.scss']
})
export class CreateRecommendationComponent implements OnInit {
  recommendation: Recommendation = {
    writerId: 0,
    receiverId: 0,
    writerName: '',
    receiverName: '',
    content: '',
    date: ''
  };

  users?: User[];
  selectedReceiver?: User;
  writer?: User;
  submitted = false;

  constructor(private userService: UserService, 
    private recommendationService: RecommendationService,
    private datePipe: DatePipe,
    private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.userService.getParticipants().subscribe(data => {
      console.log(data); 
      this.users = data;
  }, error => {
      console.log(error); 
  }); 

  this.writer = this.tokenStorageService.getUser()
  }
  saveRecommendation(): void {
    
    this.recommendation.receiverId = this.selectedReceiver?.id;
    this.recommendation.writerId = this.writer?.id;
    this.recommendation.date = this.datePipe.transform(new Date(), 'yyyy-MM-dd')!
    console.log(this.recommendation);
    console.log(this.selectedReceiver);
    
    this.recommendationService.create(this.recommendation)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newRecommendation(): void {
    this.submitted = false;
    this.recommendation = {
      writerId: 0,
      receiverId: 0,
      writerName: '',
      receiverName: '',
      content: '',
      date: ''
    };
  }

}
