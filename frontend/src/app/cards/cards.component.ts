import { Component, OnInit } from '@angular/core';
import { MatIconRegistry, MatDialog } from '@angular/material';

import { ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {
  @ViewChild('close') private cerrar: TemplateRef<object>;
  @ViewChild('dialogContent') private template: TemplateRef<object>;

  
  
  config = {
    width: '50%',
    height: '50%',
  };

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  dialogCerrar() {
    this.dialog.open(this.cerrar, this.config);
  }

  openTemplate() {
    this.dialog.open(this.template, this.config);
  }

}
