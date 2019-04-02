import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { MatIconRegistry, MatDialog, MatDialogConfig } from '@angular/material';

import { ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { Unit } from '../index/unit.model';
import { UnitService } from '../index/unit.service';
import { RelationService } from './relation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Page } from '../page.module';
import { LoginService } from '../login/login.service';
import { Relation } from './relation.model';
import {  TdDialogService } from '@covalent/core';
import { TdDialogActionsDirective } from '@covalent/core/dialogs';
import { DialogUmlComponent } from '../dialogs/dialog-uml.component';

@Component({
  selector: 'app-relation',
  templateUrl: './relation.component.html',
})
export class RelationComponent implements OnInit {
  @ViewChild('dialogContent') private template: TemplateRef<object>;



  config = {
    width: '50%',
    height: '50%',
  };
  
  units:Unit[];
  unitName:string;
  inheritance:Relation[];
  selected:Relation;
  inheritancePageNumber:number;
  inheritancelastRequestPage?:Page;
  typeRel:string;

  urlPrueba:string;
  

  constructor(private dialogCom:DialogUmlComponent,private activeRoute:ActivatedRoute,public dialog: MatDialog, private unitService: UnitService, private relationService: RelationService,private loginService:LoginService) {
    this.unitName = this.activeRoute.snapshot.params.name;
    this.setType("PADRES");
   }

  
  setType(type:string){this.typeRel=type;}
  
  ngOnInit() {
    this.inheritancePageNumber = 0;
    this.getUnits();
    
    this.getPageRelation(this.unitName,'parents',this.inheritancePageNumber);
  }

  requestNextPage() {
    this.inheritancePageNumber++;
    this.getPageRelation(this.unitName,'parents',this.inheritancePageNumber);
  }



  addRelation(name:string, nameOrigin:string, nameDestiny:string){
    let origin:Unit ={name: nameOrigin, photoComp: false, photoClas: false};
    let destiny:Unit ={name: nameDestiny, photoComp: false, photoClas: false};
    let rel:Relation = {type:"inheritance",origin:origin,destiny:destiny}; 
    this.relationService.addRelation(name,rel).subscribe(
      page => {this.inheritance = page.content;
               this.inheritancePageNumber = 0;
               this.getPageRelation(this.unitName,'parents',this.inheritancePageNumber); 
      
                },
      error => console.log(error)
    );
    
  }


  getPageRelation(unitName:string, relationName:string,pageNumber:number){
    this.relationService.getRelationsByType(unitName,relationName,pageNumber).subscribe(
      page => {this.dialog.closeAll();this.addToPage(page)},
      error => console.log(error)
    );
  }

  getUnits(){
    this.unitService.getAllUnits().subscribe(
      page => {this.units = page.content; console.log(this.units.length)},
      error => console.log(error)
    );
  }


  openTemplate() {
    this.dialog.open(this.template, this.config);
  }

  
  openDialog(unitName: string, type:string): void {
    this.dialog.open(DialogUmlComponent, {data: {imgUrl:'https://localhost:8080/api/unit/' + unitName + "/image/" + type},});
  }

  addToPage(page: Page): void {
    this.inheritancelastRequestPage = page;
    if(this.inheritancePageNumber === 0) {
      this.inheritance = page.content;
    } else {
      this.inheritance = this.inheritance.concat(page.content);
    }
  }

}
