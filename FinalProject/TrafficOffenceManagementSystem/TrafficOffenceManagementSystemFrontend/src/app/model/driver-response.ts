import { Driver } from '../model/driver';

export class DriverResponse {

    statusCode: number;
    message: string;
    data: Driver;

    constructor(statusCode: number, message: string, data: Driver){

        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
