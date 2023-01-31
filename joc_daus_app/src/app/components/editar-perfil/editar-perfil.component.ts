import { UsuariService } from './../../services/usuari.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.component.html',
  styleUrls: ['./editar-perfil.component.css']
})
export class EditarPerfilComponent implements OnInit {

  usuaris:any;
  usuari:any;

  constructor(private usuariService:UsuariService) {

  }

  ngOnInit(): void {
    this.usuariService.getUsuaris().subscribe(
      (result) =>{
        this.usuaris = result;
        console.log(this.usuaris);
        for (let i=0;i<this.usuaris.length;i++){
          if (this.usuaris[i].id == localStorage.getItem('idUsuari')){
            this.usuari = this.usuaris[i];
            console.log('Usuari',this.usuaris[i])
          }
        }
      },
      (error) =>{
        console.log(error);
      }
    );
  }

  updateUser(){
    this.usuariService.updateUser(localStorage.getItem('idUsuari'),this.usuari).subscribe(
      (result)=>{
        console.log(result);
        this.showModal();
      },
      (error) =>{
        console.log(error);
      }
    );
  }

  showModal() {
    Swal.fire({
      text: 'Usuario actualizado con éxito',
      icon: 'success',
      showCloseButton: true,
      confirmButtonText: 'Ok',
    });
  }

}
