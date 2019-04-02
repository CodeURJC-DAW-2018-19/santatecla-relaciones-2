import { Component, Inject, InjectionToken } from '@angular/core';
import {  MAT_DIALOG_DATA } from '@angular/material';

@Component({
    selector: 'app-dialog-uml',
    templateUrl: './dialog-uml.component.html',
  })
  export class DialogUmlComponent  {
      imgUrl:string;
      type?:string;

      constructor( @Inject(MAT_DIALOG_DATA) public data:any){
            this.imgUrl=data.imgUrl;
        }


 }