import { UsuariService } from './../../services/usuari.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-usuaris-ranking',
  templateUrl: './usuaris-ranking.component.html',
  styleUrls: ['./usuaris-ranking.component.css']
})
export class UsuarisRankingComponent implements OnInit {

  usuaris:any;
  usuari:any;
  usuariId = localStorage.getItem('idUsuari');

  constructor(private usuariService:UsuariService) { }

  ngOnInit(): void {
    this.mostrarRanking();
  }

  mostrarRanking(){
    this.usuariService.getUsuarisRanking().subscribe(
      (result) => {
        console.log("Result S",result);
        this.usuaris = result;
        for (let numero of this.usuaris){
          console.log(numero);
        }
      },
      (error) => {
        console.log(error);
      }
    )
  }

}
