import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TdTabSelectBase } from '@covalent/core';
import { Router } from '@angular/router';


@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})

export class AppComponent {
    mode: string;
    tabs: string[];

    constructor(private router: Router) {
        this.tabs = new Array<string>();
        if (environment.production) {
            this.mode = "Production";
        } else {
            this.mode = "Development";
        }
    }

    addTab(tabName: string) {
        if (!this.tabs.includes(tabName)) {
            this.tabs.push(tabName);
        }
    }

    deleteTab(tabName: string) {
        const index: number = this.tabs.indexOf(tabName);
        if (index !== -1) {
            this.tabs.splice(index, 1);
        }
    }

    deleteAllTabs() {
        this.tabs = new Array<string>();
    }
}
