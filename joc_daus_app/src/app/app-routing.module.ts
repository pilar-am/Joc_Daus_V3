
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IniciComponent } from '../app/components/inici/inici.component';
import { RegistreComponent } from '../app/components/registre/registre.component';
import { LoginComponent } from '../app/components/login/login.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { RankingComponent } from './components/ranking/ranking.component';

const routes: Routes = [
  { path: '', component: IniciComponent },
  { path: 'players/registre', component: RegistreComponent },
  { path: 'login', component: LoginComponent },
  { path: 'players/perfil', component: PerfilComponent },
  { path: 'players/ranking', component: RankingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
