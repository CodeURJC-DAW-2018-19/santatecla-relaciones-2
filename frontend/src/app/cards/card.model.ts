import { Unit } from '../index/unit.model';

export interface Card {
    type: string;
    unitAsoc: Unit;
    photo: boolean;
    desc: string;
    fileSelectMsg?: string;
    fileUploadMsg?: string;
    disabled?: boolean;
	files:File;
    imgUrl:string;
}

export class Card {
    type: string;
    unitAsoc: Unit;
    photo: boolean;
    desc: string;
    fileSelectMsg?: string;
    fileUploadMsg?: string;
    disabled?: boolean;
	files:File;
    imgUrl:string;

    constructor(type : string , unitAsoc : Unit, photo : boolean, desc : string){
        this.type = type;
        this.unitAsoc = unitAsoc;
        this.photo = photo;
        this.desc = desc;
    }
}