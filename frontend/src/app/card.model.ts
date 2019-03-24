import { Unit } from './unit.model';

export interface Card {
	type: string;
	unitAsoc: Unit;
 	photo: boolean;
 	desc: string;
}