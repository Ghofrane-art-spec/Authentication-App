import { Component ,OnInit} from '@angular/core';
import { User } from '../user';
import { ImageService } from '../image.service';
import { UserService } from '../user.service';
import { Image } from '../image';
import { Router } from '@angular/router';
import { LocalServiceService } from '../local-service.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrl: './display.component.css'
})
export class DisplayComponent {
  imageUrl: string | null = null;
  user!:User;


  ngOnInit(): void {
    let data=this.localStore.getData('auth');
      if(data=="1"){
        this.router.navigate(["/display"]);
      }else if(data=="2"){
        this.router.navigate(["/edit"]);
      }else{
        this.router.navigate(["/"]);
      }
    this.user = history.state.user;
    console.log(this.user);
    this.imageDisplay(this.user);
    

  }
  constructor(private imageService:ImageService,private userService:UserService,private router:Router,private localStore: LocalServiceService){}


  imageDisplay(user:User){
    this.userService.verifyUserImage(user.id).subscribe((image:Image)=>{
      this.imageService.DisplayImage(image.name).subscribe((imageData:any)=>{
        const blob = new Blob([imageData], { type: 'image/*' });
        this.imageUrl = URL.createObjectURL(blob)
    },(error)=>{
      console.log(error);
    });

    },(error)=>{
      console.log(error);
    });

  }

  
  redirect(){
    this.localStore.saveData('auth',"2");
    this.router.navigate(["/edit"],   { state: { userBeforeEdit:this.user } })
  }

  LogOut(){
    this.localStore.saveData('auth',"0");
    location.reload();
  }
  
}
