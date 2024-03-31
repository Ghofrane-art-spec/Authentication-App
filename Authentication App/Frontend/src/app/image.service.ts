import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http:HttpClient) { }

  UploadImage(image:FormData){
   return this.http.post('http://localhost:8080/upload',image);
  }

  DisplayImage(imageName:String){
    return this.http.get('http://localhost:8080/download/'+imageName, { responseType: 'arraybuffer' });
   }

   DeleteImage(id:Number){
    return this.http.delete('http://localhost:8080/delete/'+id);
   }
}
