import { Component, OnInit } from '@angular/core';
import { Recommendation } from '../models/recommendation';
import { RecommendationService } from '../services/recommendation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-create-recommendation',
  templateUrl: './create-recommendation.component.html',
  styleUrls: ['./create-recommendation.component.scss']
})
export class CreateRecommendationComponent implements OnInit {
  // user: Recommendation = {
  //   writer: '',
  //   receiver: '',
  //   content: '',
  //   date: ''
  // };
  submitted = false;

  constructor(private userService: UserService, private recommendationService: RecommendationService) { }

  ngOnInit(): void {
  }
  saveRecommendation(): void {
    // let roles : string[] = [];
    // if(this.user.role)
    //   roles.push(this.user.role);

    // const data = {
    //   writer: this.user.id,
    //   email: this.user.email,
    //   role: roles,
    //   password: this.user.password
    // };

    // console.log(data)
    // this.userService.create(data)
    //   .subscribe(
    //     response => {
    //       console.log(response);
    //       this.submitted = true;
    //     },
    //     error => {
    //       console.log(error);
    //     });
  }

  newRecommendation(): void {
    // this.submitted = false;
    // this.user = {
    //   username: '',
    //   email: '',
    //   role: '',
    //   password: ''
    // };
  }

}
