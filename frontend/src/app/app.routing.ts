import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { UnitsComponent } from './units/units.component';
import { CardsComponent } from './cards/cards.component';


const appRoutes = [
    { path: '', component: IndexComponent, useAsDefault: true},
    { path: 'units', component: UnitsComponent, },
    { path: 'cards', component: CardsComponent, }
];

export const routing = RouterModule.forRoot(appRoutes);
