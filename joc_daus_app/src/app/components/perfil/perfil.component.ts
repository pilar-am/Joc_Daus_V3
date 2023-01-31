import { PartidaService } from './../../services/partida.service';
import { UsuariService } from './../../services/usuari.service';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { subscribeOn } from 'rxjs';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  jugarpartida:boolean=false;
  mostrarpartides:boolean=false;
  editarperfil:boolean=false;
  mostrarranking:boolean=false;

  token = localStorage.getItem('token');
 // idUsuari = localStorage.getItem('idUsuari');
  idUsuari = localStorage.getItem("idUsuari");
  partidesUsuari:any;
  usuaris:any;
  usuari:any;
  tipoUsuario = localStorage.getItem('tipoUsuario');
  usuarioAdmin:boolean = false;

  constructor(private usuariService:UsuariService,private partidaService: PartidaService) { }

  ngOnInit(): void {
    console.log("El token: ",this.token);
    console.log("El id:  ", this.idUsuari);
    // this.usuariService.getUsuaris().subscribe(
    //   (result) => {
    //      this.usuaris = result;
    //      console.log("Los usuarios",this.usuaris);
    //      for(let i=0; i< this.usuaris.length; i++){
    //       if (this.usuaris[i].id == this.idUsuari){
    //         console.log("A ver ",this.usuaris[i]);

    //          this.usuari = this.usuaris[i];

    //       }
    //      }
    //      console.log("El usuario ",this.usuari);
    //         this.tipoUsuario = this.usuari.tipoUsuario;
    //         console.log("El tipo usuario ",this.tipoUsuario);

    //    },
    //    (error) =>{
    //      console.log(error);
    //    }
    // );

  }

  jugarPartida(){
    this.jugarpartida=true;
    this.mostrarpartides=false;
    this.editarperfil=false;
    this.mostrarranking=false;

  }

  mostrarPartides(){
    this.jugarpartida=false;
    this.mostrarpartides=true;
    this.editarperfil=false;
    this.mostrarranking=false;
  }

  editarPerfil(){
    this.jugarpartida=false;
    this.mostrarpartides=false;
    this.editarperfil=true;
    this.mostrarranking=false;
  }

  mostrarRanking(){
    this.jugarpartida=false;
    this.mostrarpartides=false;
    this.editarperfil=false;
    this.mostrarranking=true;
  }
  eliminarPartidas(){
    if (confirm("¿Seguro que quieres eliminar tus partidas?")){
      this.usuariService.deletePartides(localStorage.getItem('idUsuari')).subscribe(
        (result) => {
          console.log("Resultado: ", result);
          this.showModal();
          this.jugarpartida=false;
          this.mostrarpartides=false;
          this.editarperfil=false;
          this.mostrarranking=false;
        },
        (error) => {
          console.log(error);
        }
      );
    }
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


  showModal() {
    Swal.fire({
      text: 'Partidas eliminadas con éxito',
      icon: 'success',
      showCloseButton: true,
      confirmButtonText: 'Ok',
    });
  }
}
