import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { Unit } from './unit.model';

const BASE_URL = 'https://localhost:8080/api/';

@Injectable()
export class UnitService {

	constructor(private http: Http) { }

	getUnits(search: string, page: number | string) {
		let url: string = BASE_URL + "units?page=" + page;
		if(search != null){
			url += "&search=" + search;
		}
		return this.http.get(url)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getUnit(name: string) {
		return this.http.get(BASE_URL + "unit/" + name)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	addUnit(unit: Unit) {
		return this.http.post(BASE_URL + "unit", unit)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	removeUnit(name: string) {
		return this.http.delete(BASE_URL + "unit/" + name)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getUMLImage(name: string, typeImage: string) {
		//TODO response => image
		return this.http.get(BASE_URL + "unit/" + name + "/image/" + typeImage)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}