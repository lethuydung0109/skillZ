import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recommendation } from '../models/recommendation';
import { User } from '../models/user.model';
import { RecommendationService } from '../services/recommendation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  currentUser: User = {
    username: '',
    email: '',
    role: '',
    password: '',
  };
  message = '';
  currentIndex = -1;
  recommendations?: Recommendation[];

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.message = '';
    this.getUser(this.route.snapshot.params.id);
    console.log(this.currentUser);
  }

  getUser(id: string): void {
    this.userService.get(id)
      .subscribe(
        data => {
          this.currentUser = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
    this.recommendationService.get(this.route.snapshot.params.id)
    .subscribe(
      recommendations  => {
        this.recommendations = recommendations;
        
        // console.log("role = " + users[0].role);
      },
      error => {
        console.log(error);
      });
  }
  updateUser(): void {
    this.userService.update(this.currentUser.id, this.currentUser)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message;
        },
        error => {
          console.log(error);
        });
  }

  deleteUser(): void {
    this.userService.delete(this.currentUser.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/users']);
        },
        error => {
          console.log(error);
        });
  }

}
