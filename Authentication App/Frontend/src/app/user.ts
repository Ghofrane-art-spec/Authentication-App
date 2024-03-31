import { Image } from "./image";

export class User {
    id!:Number;
    email!:String;
    password!:String;
    name!:String;
    surname!:String;
    bio!:String;
    phoneNumber!:Number;
    image!:Image;
}
