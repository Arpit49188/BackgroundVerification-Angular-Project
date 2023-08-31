import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  constructor(private http:HttpClient) { }

  getUserData(username:string,password:string){
    console.log('http://localhost:8080/user/'+password+"..........url ")
    return this.http.get('http://localhost:8080/user/'+username+'/'+password);
  }
}
