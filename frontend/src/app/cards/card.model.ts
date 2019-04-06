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
