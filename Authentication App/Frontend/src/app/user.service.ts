import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Image } from './image';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

   Login(email:String,pwd:String){
     return this.http.get<User>('http://localhost:8080/login/'+email+'/'+pwd);
    }

    Register(email:String,pwd:String,name:String,surname:String,phone:Number,bio:String){
      return this.http.get<User>('http://localhost:8080/register/'+email+'/'+pwd+'/'+name+'/'+surname+'/'+phone+'/'+bio);
     }

    SaveUser(user:User,imageName:String){
    return this.http.post<User>('http://localhost:8080/register/'+imageName,user);
     }

     EditUser(id:Number,user:User){
      return this.http.put<User>('http://localhost:8080/edit/'+id,user);
    }

    verifyUserImage(id:Number){
      return this.http.get<Image>('http://localhost:8080/verify/'+id);
     }

     EditUserImage(imageName:String,user:User){
      return this.http.put<User>('http://localhost:8080/editImage/'+imageName,user);
     }

}
