import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-component',
    templateUrl: './card.component.html',
    styleUrls: ['./cards.component.css'],
  })
  export class CardComponent {
    unitName:string;
    activeLinkIndex=-1;
    constructor(private activeRoute:ActivatedRoute){
      this.unitName = this.activeRoute.snapshot.params.name;
    }
  }