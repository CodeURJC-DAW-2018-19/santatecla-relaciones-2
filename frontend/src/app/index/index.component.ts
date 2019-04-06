import { Component, OnInit } from '@angular/core';
import { Unit } from './unit.model';
import { UnitService } from './unit.service';
import { LoginService } from '../login/login.service';

import { Router } from '@angular/router';
import { Page } from '../page.module';
import { AppComponent } from '../app.component';

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
  pulsado = false;
  nameUnit : string;

  constructor(private router: Router, private service: UnitService, public loginService:LoginService, public appComponent : AppComponent) {
   }

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
      for(let i of page.content){
        if(this.units.includes(i)){
          this.units.push(i);
        }
      }
      this.units = this.units.concat(page.content);
    }
  }

  removeUnit(unit: Unit){
    this.appComponent.deleteTab(unit.name);
    const index: number = this.units.indexOf(unit);
        if (index !== -1) {
            this.units.splice(index, 1);
    }
    this.service.removeUnit(unit)
    .subscribe((_) => {}, (error) => console.error(error));
  }

  addUnitToIndex(){
    this.changePulsado();
    let unit: Unit;
    unit = {name : this.nameUnit , photoClas : false , photoComp : false};
    this.service.addUnit(unit).subscribe(
      u => {if (this.lastRequestedPage.last){
        this.units = this.units.concat([unit])
      }},
      error => console.log(error)
    );
  }

  changePulsado(){
    if(this.pulsado){
      this.pulsado = false;
    }
    else{
      this.pulsado = true;
    }
  }

}