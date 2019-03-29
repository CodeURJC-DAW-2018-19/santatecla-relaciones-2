import { Unit } from '../index/unit.model';

export interface Card {
	type: string;
	unitAsoc: Unit;
 	photo: boolean;
 	desc: string;
}