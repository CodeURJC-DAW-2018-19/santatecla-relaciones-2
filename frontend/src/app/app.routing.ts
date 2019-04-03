import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RelationComponent } from './units/relation.component';
import { CardComponent } from './cards/card.component';
import { UnitComponent } from './units/unit.component';




const appRoutes = [
    { path: '', component: IndexComponent},
    { path: 'unit/:name', component: UnitComponent, },
    { path: 'unit/:name'+'/cards', component: CardComponent, },
    { path: 'login', component: LoginComponent, },
    { path: 'register', component: RegisterComponent, }
];


export const routing = RouterModule.forRoot(appRoutes);
