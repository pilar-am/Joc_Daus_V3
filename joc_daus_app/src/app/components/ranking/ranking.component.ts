import { Component, OnInit } from '@angular/core';
import { UsuariService } from './../../services/usuari.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {

  usuaris:any;
  usuari:any;
  usuariId = localStorage.getItem('idUsuari');
  posicion = 0;
  perdedor:any;
  ganador:any;

  token:any;

  constructor(private usuariService:UsuariService) { }

  ngOnInit(): void {
    this.token = localStorage.getItem('token');
    this.mostrarRanking();
    this.ultimaPosicion();
    this.primeraPosicion();

  }


  mostrarRanking(){
    this.usuariService.getUsuarisRanking().subscribe(
      (result) => {
        this.usuaris = result;
        console.log("Usuaris ",result);
        let numPosicion =0;
        this.usuaris.forEach((element:any) => {
          if(element.nom.startsWith('ANÒNIM')){
            console.log("AQUI ANONIMs");
            element.nom = element.nom.slice(0,6);
            console.log("AAA veeer", element.nom);
          }
          numPosicion++;
          if(element.id == this.usuariId){
            this.usuari = element;
            this.posicion=numPosicion;
          }
        });
      },
      (error) => {
        console.log(error);
      }
    )
  }

  ultimaPosicion(){
    this.usuariService.getLoser().subscribe(
      (result) =>{
        this.perdedor = result;
        console.log("ultima Posición", this.perdedor);
      },
      (error) =>{
        console.log(error);
      }
    )
  }

  primeraPosicion(){
    this.usuariService.getWinner().subscribe(
      (result)=>{
        this.ganador = result;
        console.log("Primera posición ",this.ganador);
      }
    )
  }

}
