import { NgModule, TemplateRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { JsonpModule, HttpModule } from '@angular/http';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatIconRegistry } from '@angular/material/icon';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { ErrorInterceptor } from '../error.interceptor';
import { BasicAuthInterceptor } from '../auth.interceptor';

import { AppComponent } from './app.component';

import {
    MatChipsModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatInputModule,
    MatButtonToggleModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatTabsModule,
    MatSidenavModule,
    MatTooltipModule,
    MatRippleModule,
    MatRadioModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSliderModule,
    MatAutocompleteModule,
    MatSnackBar,
    MAT_DIALOG_DEFAULT_OPTIONS,
} from '@angular/material';

import {
    CovalentCommonModule,
    CovalentLayoutModule,
    CovalentMediaModule,
    CovalentExpansionPanelModule,
    CovalentStepsModule,
    CovalentLoadingModule,
    CovalentDialogsModule,
    CovalentSearchModule,
    CovalentPagingModule,
    CovalentNotificationsModule,
    CovalentMenuModule,
    CovalentVirtualScrollModule,
    CovalentDataTableModule,
    CovalentMessageModule,
    CovalentTabSelectModule,
} from '@covalent/core';
import { routing } from './app.routing';


import { NgxChartsModule } from '@swimlane/ngx-charts';
import { DomSanitizer } from '@angular/platform-browser';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UnitService } from './index/unit.service';
import { LoginService } from './login/login.service';
import { RegisterService } from './register/register.service';

import { RelationService } from './units/relation.service';
import { CardComponent } from './cards/card.component';
import { CardService } from './cards/card.service';
import { RelationComponent } from './units/relation.component';
import { DialogUmlComponent } from './dialogs/dialog-uml.component';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@NgModule({
    imports: [
        HttpModule,
        CovalentVirtualScrollModule,
        MatChipsModule,
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        RouterModule.forRoot([]),
        HttpClientModule,
        JsonpModule,
        MatSnackBarModule,
        CovalentTabSelectModule,
        /** Material Modules */
        MatButtonModule,
        MatListModule,
        MatIconModule,
        MatCardModule,
        MatMenuModule,
        MatInputModule,
        MatSelectModule,
        MatButtonToggleModule,
        MatSlideToggleModule,
        MatProgressSpinnerModule,
        MatDialogModule,
        MatSnackBarModule,
        MatToolbarModule,
        MatTabsModule,
        MatSidenavModule,
        MatTooltipModule,
        MatRippleModule,
        MatRadioModule,
        MatGridListModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSliderModule,
        MatInputModule,
        MatAutocompleteModule,
        /** Covalent Modules */
        CovalentCommonModule,
        CovalentLayoutModule,
        CovalentMediaModule,
        CovalentExpansionPanelModule,
        CovalentStepsModule,
        CovalentDialogsModule,
        CovalentLoadingModule,
        CovalentSearchModule,
        CovalentPagingModule,
        CovalentNotificationsModule,
        CovalentMenuModule,
        CovalentDataTableModule,
        CovalentMessageModule,
        /** Additional **/
        NgxChartsModule,
        routing,
    ],
    declarations: [AppComponent, IndexComponent, CardComponent, LoginComponent, RegisterComponent,RelationComponent, DialogUmlComponent],
    bootstrap: [AppComponent],
    entryComponents: [
        DialogUmlComponent,
    ],
    providers:[DialogUmlComponent,UnitService, LoginService, RegisterService,RelationService,CardService, { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },{ provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useValue: {} },]
})
export class AppModule {
    constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {
        matIconRegistry.addSvgIconSet(domSanitizer.bypassSecurityTrustResourceUrl('/assets/symbol-defs.svg'));
    }
}
