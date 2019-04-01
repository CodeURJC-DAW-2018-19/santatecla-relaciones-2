import { Component, OnInit } from '@angular/core';
import { MatIconRegistry, MatDialog } from '@angular/material';

import { ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { Unit } from '../index/unit.model';
import { UnitService } from '../index/unit.service';
import { RelationService } from '../units/relation.service';
import { Router } from '@angular/router';
import { Page } from '../page.module';
import { LoginService } from '../login/login.service';
import { Relation } from '../units/relation.model';
import { TdDialogActionsDirective } from '@covalent/core';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css'],
})
export class CardsComponent implements OnInit {
  @ViewChild('dialogContent') private template: TemplateRef<object>;



  config = {
    width: '50%',
    height: '50%',
  };

  inheritance:Relation[];
  selected:Relation;

  constructor(public dialog: MatDialog, private unitService: UnitService, private relationService: RelationService,private loginService:LoginService) { }

  ngOnInit() {
    this.getPageRelation();
  }


  openTemplate() {
    this.dialog.open(this.template, this.config);
  }

  addRelation(name:string, relation:Relation){
    console.log(name);
    console.log(relation);
    this.relationService.addRelation(name,relation).subscribe(
      page => {this.inheritance = page.content;this.getPageRelation(); },
      error => console.log(error)
    );
    
  }


  getPageRelation(){
    this.relationService.getRelationsByType('HTML','parents',0).subscribe(
      page => {this.dialog.closeAll();this.inheritance = page.content},
      error => console.log(error)
    );
  }

}
