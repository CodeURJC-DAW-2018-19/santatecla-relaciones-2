import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { Relation } from './relation.model';
import { LoginService } from '../login/login.service';
import { HttpHeaders } from '@angular/common/http';

const BASE_URL = '/api/unit/';

@Injectable()
export class RelationService {

	constructor(private http: Http, private loginService:LoginService) { }

	getContext(unitName: string, page: number | string) {
		return this.http.get(BASE_URL + unitName + "/context?page=" + page)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getRelationsByType(unitName: string, type: string, page: number | string) {
		let url =BASE_URL + unitName + "/relations/" + type + "?page=" + page;
		return this.http.get(BASE_URL + unitName + "/relations/" + type + "?page=" + page + '&size=5')
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	addRelation(unitName: string, relation: Relation) {
		//TODO improve URL in back
		return this.http.post(BASE_URL + unitName + "/relations", relation)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	removeRelation(unitName: string, type: string, unitRelated: string) {
		return this.http.delete(BASE_URL + unitName + "/relations/" + type + "/related/" + unitRelated)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}