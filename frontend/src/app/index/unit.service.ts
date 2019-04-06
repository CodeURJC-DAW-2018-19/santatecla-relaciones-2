import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { Unit } from './unit.model';
import { Page } from '../page.module';
import { catchError } from 'rxjs/operators';

const BASE_URL = '/api/';

@Injectable()
export class UnitService {

	
	constructor(private http: Http, private httpC:HttpClient) { }

	
	getUnits(search: string, page: number | string): Observable<Page> {
			let url: string = BASE_URL + "units?page=" + page;
			if(search != null){
				url += "&search=" + search;
			}
		return this.http.get(url)
			.map(response => response.json())
			.catch(error => this.handleError(error));

	}

	getAllUnits(): Observable<Page> {
		let url: string = BASE_URL + "units" + '?size=99999';

	return this.http.get(url)
		.map(response => response.json())
		.catch(error => this.handleError(error));

	}


	getUnitSearch(search: string): Observable<Page> {
		let url: string = BASE_URL + "units?search="+search;
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
		console.log("se esta añadiendo la unidad de nombre " + unit.name)
		return this.http.post(BASE_URL + "unit", unit)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	removeUnit(unit: Unit) {
		return this.httpC.delete<Unit>(BASE_URL + "unit/" + unit.name)
			.pipe(catchError((error) => this.handleError(error)));
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