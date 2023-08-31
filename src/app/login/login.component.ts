import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  getData: any ;
  model: any={};
  
  
  loginForm!: FormGroup;
  constructor( private userservice : UserserviceService, private router:Router){

  }
  ngOnInit(){
    
  }
  loginUser() {
   

    var user = this.model.username;
    var password = this.model.password;
    console.log(this.model);

     
    this.userservice.getUserData(user, password).subscribe((res: any) => {
        this.getData = res;
        console.log("flag "+this.getData)
        

        if (this.getData != -1) {
         
         this.router.navigate(["/home"])
         
        } else {
         alert("Invalid users")
        }
      });
}
}
