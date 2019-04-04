import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { environment } from 'src/environments/environment';


@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent {
    mode: string;
    constructor() {
        if(environment.production){
             this.mode = "Production";
        } else {
            this.mode = "Development";
        }
    }
}
