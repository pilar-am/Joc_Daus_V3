import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegistreComponent } from './components/registre/registre.component';
import { LoginComponent } from './components/login/login.component';
import { IniciComponent } from './components/inici/inici.component';

//Para poder usar los servicios
import { HttpClient, HttpClientModule } from '@angular/common/http';

//Para poder usar los forms de angular
import { FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PerfilComponent } from './components/perfil/perfil.component';
import { PartidaComponent } from './components/partida/partida.component';
import { EditarPerfilComponent } from './components/editar-perfil/editar-perfil.component';
import { MostrarPartidesComponent } from './components/mostrar-partides/mostrar-partides.component';
import { UsuarisRankingComponent } from './components/usuaris-ranking/usuaris-ranking.component';
import { RankingComponent } from './components/ranking/ranking.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    RegistreComponent,
    LoginComponent,
    IniciComponent,
    PerfilComponent,
    PartidaComponent,
    EditarPerfilComponent,
    MostrarPartidesComponent,
    UsuarisRankingComponent,
    RankingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule.withConfig({warnOnNgModelWithFormControl: 'never'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
