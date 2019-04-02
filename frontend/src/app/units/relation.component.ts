import { Component, OnInit } from '@angular/core';
import { MatIconRegistry, MatDialog } from '@angular/material';

import { ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { Unit } from '../index/unit.model';
import { UnitService } from '../index/unit.service';
import { RelationService } from './relation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Page } from '../page.module';
import { LoginService } from '../login/login.service';
import { Relation } from './relation.model';
import { TdDialogActionsDirective } from '@covalent/core';

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

  unitName:string;
  inheritance:Relation[];
  selected:Relation;
  inheritancePageNumber:number;
  inheritancelastRequestPage?:Page;
  typeRel:string;
  

  constructor(private activeRoute:ActivatedRoute,public dialog: MatDialog, private unitService: UnitService, private relationService: RelationService,private loginService:LoginService) {
    this.unitName = this.activeRoute.snapshot.params.name;
    this.setType("PADRES");
   }

  
  setType(type:string){this.typeRel=type;}
  
  ngOnInit() {
    this.inheritancePageNumber = 0;
    this.getPageRelation(this.unitName,'parents',this.inheritancePageNumber);
  }

  requestNextPage() {
    this.inheritancePageNumber++;
    this.getPageRelation(this.unitName,'parents',this.inheritancePageNumber);
  }



  addRelation(name:string, relation:Relation){
    console.log(name);
    console.log(relation);
    this.relationService.addRelation(name,relation).subscribe(
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


  openTemplate() {
    this.dialog.open(this.template, this.config);
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
