import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../login/login.service';

@Injectable()

export class RegisterService {
    constructor(private http:HttpClient){}



    register(user:string,pass1:string,pass2:string){
        const headers = new HttpHeaders({
            Authorization: 'Basic ' + user + ':' + pass1,
            'X-Requested-With': 'XMLHttpRequest',
        });

        let url = "/api/register/"+user+"/"+pass1+"/"+pass2;

        return this.http.post<User>(url, { headers })

    }

}