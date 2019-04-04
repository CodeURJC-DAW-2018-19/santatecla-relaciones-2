import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Card } from './card.model';
import { Page } from '../page.module';
import { CardService } from './card.service';
import { LoginService } from '../login/login.service';

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

  fileSelectMsg: string = 'No file selected yet.';
  fileUploadMsg: string = 'No file uploaded yet.';
  disabled: boolean = false;

  selectEvent(file: File): void {
    this.fileSelectMsg = file.name;
  }

  uploadEvent(file: File): void {
    this.fileUploadMsg = file.name;
  }

  cancelEvent(): void {
    this.fileSelectMsg = 'No file selected yet.';
    this.fileUploadMsg = 'No file uploaded yet.';
  }

  toggleDisabled(): void {
    this.disabled = !this.disabled;
  }
  constructor(private activeRoute: ActivatedRoute, private service: CardService, private loginService: LoginService) {
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