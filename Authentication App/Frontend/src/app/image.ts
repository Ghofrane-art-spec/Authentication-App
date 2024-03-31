import { User } from "./user";

export class Image {
    id!:Number;
    name!:String;
    type!:String;
    imageData!:ArrayBuffer;
    user!:User;
}
