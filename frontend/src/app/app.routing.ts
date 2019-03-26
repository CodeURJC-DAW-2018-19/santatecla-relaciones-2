import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { UnitsComponent } from './units/units.component';
import { CardsComponent } from './cards/cards.component';
import { LoginComponent } from './login/login.component';



const appRoutes = [
    { path: '', component: IndexComponent},
    { path: 'units', component: UnitsComponent, },
    { path: 'cards', component: CardsComponent, },
    { path: 'login', component: LoginComponent, }
];

export const routing = RouterModule.forRoot(appRoutes);
