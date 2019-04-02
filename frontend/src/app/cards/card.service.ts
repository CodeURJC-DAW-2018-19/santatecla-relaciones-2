import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { Card } from './card.model';
import { ActivatedRoute } from '@angular/router';

const BASE_URL = '/api/unit/';

@Injectable()
export class CardService {

	constructor(private http: Http) {
	 }

	getCards(unitName: string, page: number | string) {
		return this.http.get(BASE_URL + unitName + "/cards?page=" + page)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getCard(unitName: string, type: string) {
		return this.http.get(BASE_URL + unitName + "/card/" + type)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	addCard(unitName: string, card: Card) {
		return this.http.post(BASE_URL + unitName + "/card", card)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	removeCard(unitName: string, type: string) {
		return this.http.delete(BASE_URL + unitName + "/card/" + type)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getImage(unitName: string, type: string) {
		//TODO response => image
		return this.http.get(BASE_URL + unitName + "/card/" + type + "/image")
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	addImage(unitName: string, type: string) {
		//TODO post image
		//TODO response => image
		/*
		return this.http.post(BASE_URL + unitName + "/card/" + type + "/image")
			.map(response => response.json())
			.catch(error => this.handleError(error));
		*/
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}