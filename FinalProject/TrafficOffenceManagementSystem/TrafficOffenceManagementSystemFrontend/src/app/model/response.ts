import { Officer } from '../model/officer';

export class Response {

    statusCode: number;
    message: string;
    data: Officer;

    constructor(statusCode: number, message: string, data: Officer){

        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
}

}
