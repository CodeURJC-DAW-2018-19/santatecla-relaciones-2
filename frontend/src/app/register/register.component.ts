import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material';
import { RegisterService } from './register.service';
import { LoginService } from '../login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router:Router,private snackBar: MatSnackBar, private registerService:RegisterService, private loginService:LoginService) { 
    
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  ngOnInit() {
  }

  register(userName:string,pass1:string,pass2:string){
    this.registerService.register(userName,pass1,pass2).subscribe(
        user=>this.router.navigate(["/"]),
        (error)=>alert('Error en el registro.'),
    ) 
  }
}
