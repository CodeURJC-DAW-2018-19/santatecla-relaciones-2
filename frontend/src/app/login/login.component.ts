import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import {MatSnackBar, MatDialogRef, MatDialog} from '@angular/material';
import { LoginService } from './login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {
  @ViewChild('loginDialog') loginDialog: TemplateRef<any>;
  dialogRef: MatDialogRef<any, any>;

  constructor(public dialog: MatDialog,private snackBar: MatSnackBar, private loginService:LoginService, private router:Router) { 
  }

  openLoginDialog() {
    this.dialogRef = this.dialog.open(this.loginDialog, {
        width: '50%',
        height: '50%',
    });
}

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  logIn(event: any, user: string, pass: string) {
    event.preventDefault();

    this.loginService.logIn(user, pass).subscribe(
        (u) => {
            console.log(u);
            this.dialogRef.close();
        },
        (error) => alert('Invalid user or password'),
    );
}

logOut() {
    this.loginService.logOut().subscribe(
        (response) => {
            this.router.navigate(['/']);
        },
        (error) => console.log('Error when trying to log out: ' + error),
    );
}

register(){
  this.dialogRef.close();
  this.router.navigate(['/register']);
}


}
