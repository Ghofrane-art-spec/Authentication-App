import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { User } from '../user';
import { LocalServiceService } from '../local-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email!:String;
  pwd!:String;

  ngOnInit(): void {
    this.localStore.saveData('auth',"0");
  }
  constructor(private userService:UserService, private router:Router,private localStore: LocalServiceService ){}

  Login(){
    this.userService.Login(this.email,this.pwd).subscribe((user:User)=>{
      if(user==null){
        alert("email or password incorrect");
      }else{
        this.localStore.saveData('auth',"1");
        this.router.navigate(["/display"],   { state: { user } } )
      }
    },(error)=>{
      console.log(error);
    })
  }

}
