import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Card } from './card.model';
import { Page } from '../page.module';
import { CardService } from './card.service';

@Component({
    selector: 'app-component',
    templateUrl: './card.component.html',
    styleUrls: ['./cards.component.css'],
  })

  export class CardComponent implements OnInit{
    unitName:string;
    activeLinkIndex=-1;
    cards: Card[];
    lastRequestedPage:Page;
    pageNumber:number;
    
    constructor(private activeRoute:ActivatedRoute, private service:CardService){
      this.unitName = this.activeRoute.snapshot.params.name;
    }

    ngOnInit(): void {
      this.pageNumber = 0;
      this.getPage();
    }

    requestNextPage() {
      this.pageNumber++;
      this.getPage();
    }

    getPage() {
      this.service.getCards(this.unitName, this.pageNumber).subscribe(
        page => {
          this.lastRequestedPage = page;
          if(this.pageNumber === 0) {
            this.cards = page.content;
          } else {
            this.cards = this.cards.concat(page.content);
          }
        },
        error => console.log(error)
      );
    }
  }