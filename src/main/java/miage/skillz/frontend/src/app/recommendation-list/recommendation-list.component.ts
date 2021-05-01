import { Component, OnInit } from '@angular/core';
import { Recommendation } from '../models/recommendation';
import { User } from '../models/user.model';
import { RecommendationService } from '../services/recommendation.service';
import { TokenStorageService } from '../_services/auth/service/token-storage.service';

@Component({
  selector: 'app-recommendation-list',
  templateUrl: './recommendation-list.component.html',
  styleUrls: ['./recommendation-list.component.scss']
})
export class recommendationListComponent implements OnInit {
  recommendations?: Recommendation[];
  currentRecommendation?: Recommendation;
  currentUser?: User;
  currentIndex = -1;
  writer = '';

  constructor(private recommendationService: RecommendationService,
    private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.retrieveRecommendations();
  }

  retrieveRecommendations(): void {
    this.currentUser = this.tokenStorageService.getUser();
    this.recommendationService.get(this.currentUser?.id)
      .subscribe(
        recommendations  => {
          this.recommendations = recommendations;
          
          // console.log("role = " + users[0].role);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveRecommendations();
    this.currentRecommendation = undefined;
    this.currentIndex = -1;
  }

  setActiveRecommendation(recommendation: Recommendation, index: number): void {
    this.currentRecommendation = recommendation;
    this.currentIndex = index;
  }

  removeAllRecommendations(): void {
    this.recommendationService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  // searchUsername(): void {
  //   if(this.username != ""){
  //     this.userService.findByUsername(this.username)
  //     .subscribe(
  //       data => {
  //         this.users = data;
  //         console.log(this.users);
  //       },
  //       error => {
  //         console.log(error);
  //       });
  //   }else{
  //     this.retrieveUsers();
  //   }
    
  }



