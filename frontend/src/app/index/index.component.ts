import { Component, OnInit } from '@angular/core';
import { Unit } from './unit.model';
import { UnitService } from './unit.service';
import { LoginService } from '../login/login.service';

import { Router } from '@angular/router';
import { Page } from '../page.module';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
  
})

export class IndexComponent implements OnInit {
  units:Unit[];
  lastRequestedPage:Page;
  pageNumber:number;
  searchInputTerm:string;

  constructor(private router: Router, private service: UnitService, public loginService:LoginService) { }

  ngOnInit() {
    this.pageNumber = 0;
    this.getPage();
  }

  requestNextPage() {
    this.pageNumber++;
    this.getPage();
  }

  getPage() {
    this.service.getUnits(this.searchInputTerm,this.pageNumber).subscribe(
      page => this.addToPage(page),
      error => console.log(error)
    );
  }

  getPageSearch(){
    this.service.getUnitSearch(this.searchInputTerm).subscribe(
      page => {
        this.units = page.content;
        this.pageNumber=0;
        this.lastRequestedPage = page;},
      error => console.log(error)
    );
  }

  addToPage(page: Page): void {
    this.lastRequestedPage = page;
    if(this.pageNumber === 0) {
      this.units = page.content;
    } else {
      this.units = this.units.concat(page.content);
    }
  }

  removeUnit(unit: Unit){
    this.service.removeUnit(unit)
    .subscribe((_) => this.getPage(), (error) => console.error(error));
  }


}