import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';

import { ViewChild, TemplateRef } from '@angular/core';
import { Unit } from '../index/unit.model';
import { RelationService } from './relation.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Page } from '../page.module';
import { LoginService } from '../login/login.service';
import { Relation } from './relation.model';

@Component({
  selector: 'app-relation',
  templateUrl: './relation.component.html',
})

export class RelationComponent implements OnInit {
  @ViewChild('dialogContent') private template: TemplateRef<object>;
  @ViewChild('dialogContentReverse') private templateReverse: TemplateRef<object>;

  config = {
    width: '50%',
    height: '50%',
  };

  units: Unit[];
  unitName: string;
  inheritance: Relation[];
  selected: Relation;
  pageNumber: number;
  lastRequestedPage?: Page;

  typeTitle?: string; //PADRES, HIJAS...
  typeRelLinksGlobal?: string;  //inheritance, parts, etc.
  typeRelLinksConcrete?: string; //parents, sons...

  reverse: boolean;

  constructor(private activeRoute: ActivatedRoute, public dialog: MatDialog, private relationService: RelationService, public loginService: LoginService) {
    this.unitName = this.activeRoute.snapshot.params.name;
  }

  ngOnInit() {
    if (this.loginService.isLogged) {
      this.activeRoute.paramMap.subscribe((params: ParamMap) => {
        this.unitName = params.get('name');
        if (this.typeRelLinksConcrete.localeCompare("parents") == 0 || this.typeRelLinksConcrete.localeCompare("composites") == 0
          || this.typeRelLinksConcrete.localeCompare("associatedBy") == 0 || this.typeRelLinksConcrete.localeCompare("usedBy") == 0) {
          this.reverse = false;
        } else if (this.typeRelLinksConcrete.localeCompare("children") == 0 || this.typeRelLinksConcrete.localeCompare("parts") == 0 ||
          this.typeRelLinksConcrete.localeCompare("associatedTo") == 0 || this.typeRelLinksConcrete.localeCompare("uses") == 0) {
          this.reverse = true;
        }
        this.pageNumber = 0;
        this.getPageRelation(this.unitName, this.pageNumber);
      });
    }
  }

  requestNextPage() {
    this.pageNumber++;
    this.getPageRelation(this.unitName, this.pageNumber);
  }

  addRelation(name: string, nameOrigin: string, nameDestiny: string) {
    let origin: Unit = { name: nameOrigin, photoComp: false, photoClas: false };
    let destiny: Unit = { name: nameDestiny, photoComp: false, photoClas: false };
    let rel: Relation = { type: this.typeRelLinksGlobal, origin: origin, destiny: destiny };

    this.relationService.addRelation(name, rel).subscribe(
      page => {
        this.inheritance = page.content;
        this.pageNumber = 0;
        this.getPageRelation(this.unitName, this.pageNumber);

      },
      error => console.log(error)
    );

  }

  openTemplate() {
    if (!this.reverse) {
      this.dialog.open(this.template, this.config);
    } else if (this.reverse) {
      this.dialog.open(this.templateReverse, this.config);

    }
  }

  getPageRelation(unitName: string, pageNumber: number) {
    this.relationService.getRelationsByType(unitName, this.typeRelLinksConcrete, pageNumber).subscribe(
      page => { this.dialog.closeAll(); this.addToPage(page) },
      error => console.log(error)
    );
  }

  addToPage(page: Page): void {
    this.lastRequestedPage = page;
    if (this.pageNumber === 0) {
      this.inheritance = page.content;
    } else {
      this.inheritance = this.inheritance.concat(page.content);
    }
  }

  removeRelation(unitName: string, type: string, unitRelated: string) {
    this.relationService.removeRelation(unitName, type, unitRelated).subscribe(
      (_) => { this.getPageRelation(this.unitName, this.pageNumber) },
      (error) => console.error(error)
    )
  }

}
