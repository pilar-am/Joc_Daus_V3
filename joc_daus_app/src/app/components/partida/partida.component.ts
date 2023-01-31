import { PartidaService } from './../../services/partida.service';
import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Partida} from '../../models/partida';

@Component({
  selector: 'app-partida',
  templateUrl: './partida.component.html',
  styleUrls: ['./partida.component.css']
})
export class PartidaComponent implements OnInit {
  partida = new Partida();
  partidaActualizada:any;
  resultado: boolean = false;


  constructor(private partidaService: PartidaService, private router: Router) { }

  ngOnInit(): void {
  }

  lanzarDados(){
    this.partida.tirada1 = 0
    this.partida.tirada2 = 0
    console.log("Partida: ",this.partida);
    this.partidaService.nuevaPartida(this.partida).subscribe(
      (result)=>{
        console.log("Resultado: ", result);
        this.partidaActualizada = result;
        console.log("La partida ", this.partidaActualizada);
        this.resultado = true;
      },
      (error)=>{
        console.log("Error: ",error);
      }
    )
  }

  volverJugar(){
    this.resultado=false;
  }
}
