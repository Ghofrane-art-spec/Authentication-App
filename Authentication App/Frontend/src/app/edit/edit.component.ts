import { Component } from '@angular/core';
import { User } from '../user';
import { ImageService } from '../image.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { Image } from '../image';
import { LocalServiceService } from '../local-service.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.css'
})
export class EditComponent {
  imageUrl: string | null = null;
  userBeforeEdit!:User;

  image: File | null = null;
  name!:String;
  surname!:String;
  bio!:String;
  phoneNumber!:Number;
  email!:String;
  password!:String;


  ngOnInit(): void {
    let data=this.localStore.getData('auth');
      if(data=="1"){
        this.router.navigate(["/display"]);
      }else if(data=="2"){
        this.router.navigate(["/edit"]);
      }else{
        this.router.navigate(["/"]);
      }
    this.userBeforeEdit = history.state.userBeforeEdit;
    this.imageDisplay(this.userBeforeEdit);
    
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

  LogOut(){
    this.localStore.saveData('auth',"0");
    location.reload();
  }

  onFileSelected(event: any) {
    const files: FileList = event.target.files;
    if (files && files.length > 0) {
      this.image = files[0]; 
    }
  }
  
  Edit(){
    this.userService.verifyUserImage(this.userBeforeEdit.id).subscribe((imageId:Image)=>{
      console.log(imageId);
      this.imageService.DeleteImage(imageId.id).subscribe(()=>{
        if(this.image!=null){
          var imageName=this.image?.name;
          const formData: FormData = new FormData();
          formData.append('image', this.image);
          this.imageService.UploadImage(formData).subscribe(()=>{
          console.log("image uploaded");
          this.userService.Register(this.email,this.password,this.name,this.surname,this.phoneNumber,this.bio).subscribe((newUser:User)=>{
            this.userService.EditUser(this.userBeforeEdit.id,newUser).subscribe((editedUser:User)=>{
              this.userService.EditUserImage(imageName,editedUser).subscribe((user:User)=>{
                alert("User updated");
                this.localStore.saveData('auth',"1");
                this.router.navigate(["/display"], { state: { user } });
              },(error)=>{
                console.log(error);
              })
            },(error)=>{
              console.log(error);
            })
          },(error)=>{
            console.log(error);
          })
          },(error)=>{
            console.log(error);
          })
        }
      },(error)=>{
        console.log(error);
      })
    },(error)=>{
      console.log(error);
    })
  }
}
