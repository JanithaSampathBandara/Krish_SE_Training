import { Fine } from '../model/fine';

export class FineResponse {

    statusCode: number;
    message: string;
   // data: Fine[];
    data: Array<Fine> = [];

    constructor(statusCode: number, message: string, data: Array<Fine>){

        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
