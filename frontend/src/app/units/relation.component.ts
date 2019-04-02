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
      page => {this.dialog.closeAll();this.inheritance = page.content},
      error => console.log(error)
    );
  }


  openTemplate() {
    this.dialog.open(this.template, this.config);
  }

  
  openDialog(unitName: string, type:string): void {
    this.dialog.open(DialogUmlComponent, {data: {imgUrl:'https://localhost:8080/api/unit/' + unitName + "/image/" + type},});
  }

}
