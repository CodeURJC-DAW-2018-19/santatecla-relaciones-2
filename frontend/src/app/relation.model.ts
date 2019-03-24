import { Unit } from './unit.model';

export interface Relation {
	type: string;
	origin: Unit;
	destiny: Unit;
}