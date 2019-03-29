import { Unit } from '../index/unit.model';

export interface Relation {
	type: string;
	origin: Unit;
	destiny: Unit;
}