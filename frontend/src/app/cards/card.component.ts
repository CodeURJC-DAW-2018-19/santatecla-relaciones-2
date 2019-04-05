import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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

  selectEvent(file:File, card:Card): void {
    card.fileSelectMsg=file.name;
  }

  uploadEvent(file: File,card:Card): void {
    card.fileUploadMsg=file.name;
    console.log(card.files);
    this.service.addImage(card.files,this.unitName,card.type).subscribe(
      u=>console.log(u),
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


  constructor(private activeRoute: ActivatedRoute, private service: CardService, public loginService: LoginService, private appComponent: AppComponent) {
    this.unitName = this.activeRoute.snapshot.params.name;
  }

  ngOnInit(): void {
    this.pageNumber = 0;
    this.getPage();
    this.appComponent.addTab(this.unitName);
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

  saveChanges(type: string) {
    //TODO
    console.log(type);
  }
}