import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Card } from './card.model';
import { Page } from '../page.module';
import { CardService } from './card.service';
import { LoginService } from '../login/login.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-component',
  templateUrl: './card.component.html',
  styleUrls: ['./cards.component.css'],
})

export class CardComponent implements OnInit {
  unitName: string;
  activeLinkIndex = -1;
  cards: Card[];
  lastRequestedPage: Page;
  pageNumber: number;


  


  constructor(private cdRef: ChangeDetectorRef,private router:Router,private activeRoute: ActivatedRoute, private service: CardService, public loginService: LoginService, private appComponent: AppComponent) {
    this.unitName = this.activeRoute.snapshot.params.name;
  }

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe((params: ParamMap) => {
      this.unitName = params.get('name')});
      this.pageNumber = 0;
      this.getPage();
      this.appComponent.addTab(this.unitName);
    
  }

  selectEvent(file:File, card:Card): void {
    card.fileSelectMsg=file.name;
  }

  uploadEvent(file: File,card:Card): void {
    card.fileUploadMsg=file.name;
    console.log(card.files);
    this.service.addImage(card.files,this.unitName,card.type).subscribe(
      u=>{console.log(u), this.getOneCard(this.unitName,card.type)},
      error=> console.log(error)
    );
    
    }

  cancelEvent(card:Card): void {
    card.fileSelectMsg = 'No file selected yet.';
    card.fileUploadMsg = 'No file uploaded yet.';
  }

  toggleDisabled(card:Card): void {
    card.disabled = !card.disabled;
  }
  requestNextPage() {
    this.pageNumber++;
    this.getPage();
  }

  getPage() {
    this.service.getCards(this.unitName, this.pageNumber).subscribe(
      page => {
        this.lastRequestedPage = page;
        if (this.pageNumber === 0) {
          this.cards = page.content;
        } else {
          this.cards = this.cards.concat(page.content);
        }
      },
      error => console.log(error)
    );
  }

  getOneCard(unitName: string, type: string){
    this.service.getCard(unitName,type).subscribe(
        card=>{    console.log(this.position(card));
          var start_index = this.position(card);
          var number_of_elements_to_remove = 1;
          this.cards.splice(start_index, number_of_elements_to_remove, card)},
        error=>console.log(error)
    );
  }

  position(card:Card){
    let num = 0;
    let sol=-1;
    for (let i of this.cards){
      if (i.type===card.type){
          sol=num;
      }else{
        num++;
      }
    }
    return sol;
  }

  saveChanges(type: string) {
    //TODO
    console.log(type);
  }
}