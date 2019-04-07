import { Injectable } from '@angular/core';
import { Http, ResponseContentType } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { Card } from './card.model';
import { Page } from '../page.module';

const BASE_URL = '/api/unit/';

@Injectable()
export class CardService {

	constructor(private http: Http) {
	 }

	getCards(unitName: string, page: number | string) {
		return this.http.get(BASE_URL + unitName + "/cards?size=5&page=" + page)
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
		return this.http.get(BASE_URL + unitName + "/card/" + type + "/image")
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	addImage(file:File,unitName: string, type: string) {
		let formdata: FormData = new FormData();
    	formdata.append('file', file);
        return this.http.post(BASE_URL + unitName + "/card/" + type + "/image",formdata);
	}
	
	saveCard(unitName:string,card:Card){
		return this.http.put(BASE_URL + unitName+"/card",card);
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}