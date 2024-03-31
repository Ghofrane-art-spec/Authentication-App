import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { ImageService } from '../image.service';
import { LocalServiceService } from '../local-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  image: File | null = null;
  name!:String;
  surname!:String;
  bio!:String;
  phone!:Number;
  email!:String;
  pwd!:String;

  constructor(private userService:UserService,private imageService:ImageService,private router:Router,private localStore: LocalServiceService){}

  ngOnInit(): void {
    this.localStore.saveData('auth',"0");
  }

onFileSelected(event: any) {
  const files: FileList = event.target.files;
  if (files && files.length > 0) {
    this.image = files[0]; 
  }
}




  Register(){
    this.userService.Register(this.email,this.pwd,this.name,this.surname,this.phone,this.bio).subscribe((Newuser:User)=>{
      if(Newuser==null){
        alert("email already exists");
      }else{
        if(this.image!=null){
          var imageName=this.image?.name;
          const formData: FormData = new FormData();
          formData.append('image', this.image);
          this.imageService.UploadImage(formData).subscribe(()=>{
            this.userService.SaveUser(Newuser,imageName).subscribe((user:User)=>{
              this.localStore.saveData('auth',"1");
              this.router.navigate(["/display"],   { state: { user } } );
            },(error)=>{
            })
          },(error)=>{
          })
        }else{
          alert("You have to choose an image");
        }
      }
    },(error)=>{
      console.log(error);
    })
  }
}

