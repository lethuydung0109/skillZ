import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {
  user: User = {
    username: '',
    email: '',
    role: '',
    password: ''
  };
  submitted = false;
  


  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }
  saveUser(): void {
    let roles : string[] = [];
    if(this.user.role)
      roles.push(this.user.role);

    const data = {
      username: this.user.username,
      email: this.user.email,
      role: roles,
      password: this.user.password
    };

    console.log(data)
    this.userService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newUser(): void {
    this.submitted = false;
    this.user = {
      username: '',
      email: '',
      role: '',
      password: ''
    };
  }

}
