import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';
import { single, multi, pie, times } from './data';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    animations: [tdRotateAnimation],
})
export class AppComponent implements AfterViewInit {
    @ViewChild('manageList') manageList: TdLayoutManageListComponent;
    @ViewChild('dialogContent') template: TemplateRef<any>;

    name = 'UI Platform';

    pie: any[];
    single: any[];
    multi: any[];
    times: any[];

    miniNav: boolean = false;

    // Theme toggle
    get activeTheme(): string {
        return localStorage.getItem('theme');
    }
    theme(theme: string): void {
        localStorage.setItem('theme', theme);
    }

    // Timeframe
    dateFrom: Date = new Date(new Date().getTime() - 2 * 60 * 60 * 24 * 1000);
    dateTo: Date = new Date(new Date().getTime() - 1 * 60 * 60 * 24 * 1000);

    // Dialog
    config = {
        width: '50%',
        height: '50%',
    };

    // Nav
    routes = [
        {
            title: 'Dashboards',
            route: '/',
            icon: 'dashboard',
        },
        {
            title: 'Reports',
            route: '/',
            icon: 'insert_chart',
        },
        {
            title: 'Sales',
            route: '/',
            icon: 'account_balance',
        },
        {
            title: 'Marketplace',
            route: '/',
            icon: 'store',
        },
    ];
    usermenu = [
        {
            title: 'Profile',
            route: '/',
            icon: 'account_box',
        },
        {
            title: 'Settings',
            route: '/',
            icon: 'settings',
        },
    ];

    // Data table
    data: any[] = [
        {
            name: 'Frozen yogurt',
            type: 'Ice cream',
            calories: 159.0,
            fat: 6.0,
            carbs: 24.0,
            protein: 4.0,
        },
        {
            name: 'Ice cream sandwich',
            type: 'Ice cream',
            calories: 237.0,
            fat: 9.0,
            carbs: 37.0,
            protein: 4.3,
        },
        {
            name: 'Eclair',
            type: 'Pastry',
            calories: 262.0,
            fat: 16.0,
            carbs: 24.0,
            protein: 6.0,
        },
        {
            name: 'Cupcake',
            type: 'Pastry',
            calories: 305.0,
            fat: 3.7,
            carbs: 67.0,
            protein: 4.3,
        },
        {
            name: 'Jelly bean',
            type: 'Candy',
            calories: 375.0,
            fat: 0.0,
            carbs: 94.0,
            protein: 0.0,
        },
        {
            name: 'Lollipop',
            type: 'Candy',
            calories: 392.0,
            fat: 0.2,
            carbs: 98.0,
            protein: 0.0,
        },
        {
            name: 'Honeycomb',
            type: 'Other',
            calories: 408.0,
            fat: 3.2,
            carbs: 87.0,
            protein: 6.5,
        },
        {
            name: 'Donut',
            type: 'Pastry',
            calories: 452.0,
            fat: 25.0,
            carbs: 51.0,
            protein: 4.9,
        },
        {
            name: 'KitKat',
            type: 'Candy',
            calories: 518.0,
            fat: 26.0,
            carbs: 65.0,
            protein: 7.0,
        },
        {
            name: 'Chocolate',
            type: 'Candy',
            calories: 518.0,
            fat: 26.0,
            carbs: 65.0,
            protein: 7.0,
        },
        {
            name: 'Chamoy',
            type: 'Candy',
            calories: 518.0,
            fat: 26.0,
            carbs: 65.0,
            protein: 7.0,
        },
    ];

    constructor(
        public media: TdMediaService,
        public dialog: MatDialog,
        private _changeDetectorRef: ChangeDetectorRef,
        private _iconRegistry: MatIconRegistry,
        private _domSanitizer: DomSanitizer,
    ) {
        this._iconRegistry.addSvgIconInNamespace(
            'assets',
            'covalent',
            this._domSanitizer.bypassSecurityTrustResourceUrl(
                'https://raw.githubusercontent.com/Teradata/covalent-quickstart/develop/src/assets/icons/covalent.svg',
            ),
        );

        Object.assign(this, { pie, single, multi, times });
    }

    ngAfterViewInit(): void {
        // broadcast to all listener observables when loading the page
        this.media.broadcast();
        this._changeDetectorRef.detectChanges();
    }

    toggleMiniNav(): void {
        this.miniNav = !this.miniNav;
        setTimeout(() => {
            // this.manageList.sidenav._animationStarted.emit();
        });
    }

    openTemplate() {
        this.dialog.open(this.template, this.config);
    }

    // NGX Charts Axis
    axisDigits(val: any): any {
        return new TdDigitsPipe().transform(val);
    }

    axisDate(val: string): string {
        return new DatePipe('en').transform(val, 'hh a');
    }
}
