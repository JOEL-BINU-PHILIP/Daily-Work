import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService, User } from '../../services/user';


@Component({
  selector: 'app-user-add',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-add.html',
})
export class UserAdd {

  user = {
    name: '',
    email: ''
  };

  constructor(private userService: UserService) {}

  addUser() {
    this.userService.addUser(this.user).subscribe(() => {
      alert('User added');
      this.user = { name: '', email: '' };
    });
  }
}
