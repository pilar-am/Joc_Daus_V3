import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { PartidaService } from './../../services/partida.service';

@Component({
  selector: 'app-mostrar-partides',
  templateUrl: './mostrar-partides.component.html',
  styleUrls: ['./mostrar-partides.component.css']
})
export class MostrarPartidesComponent implements OnInit {

  partidesUsuari:any;

  constructor(private partidaService: PartidaService, private router: Router) { }

  ngOnInit(): void {
    this.mostrarPartidesUsuari();
  }

  mostrarPartidesUsuari(){
    this.partidaService.mostrarPartidesUsuari(localStorage.getItem('idUsuari')).subscribe(
      (result) =>{
        this.partidesUsuari = result;
        console.log(result);
      },
      (error) =>{
        console.log(error);
      }
    )
  }

}
