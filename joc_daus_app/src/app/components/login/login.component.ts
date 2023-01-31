import { Credenciales } from './../../models/credenciales';
import { UsuariService } from './../../services/usuari.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form:Credenciales = {
    email: '',
    password:''
  };

  usuaris:any;
  usuari:any;
  idUsuari:any;
  tipoUsuario:any;

  constructor(private usuariService: UsuariService, private router: Router) {


  }

  ngOnInit(): void { }

  login(){
    console.log("Email: ", this.form.email);
    console.log("Email: ", this.form.password);
    console.log("Mi token sin nada? ",localStorage.getItem('token'));
    this.usuariService.login(this.form)
      .subscribe(
        (result) => {
          console.log("En metodo login", result)
        },
        (error) => {
          //console.log("Error login: ",error.error.text);
          console.log("Error cuando no login: ",error);
          console.log("Error cuando no login: ",error.status);
          if(error.status == 403){
            this.showModal();
            this.router.navigate(['/']);
          }else{
            localStorage.setItem("token",error.error.text);
            this.recollirUsuari();
            this.router.navigate(['/players/perfil']);
          }
        }
      );
  }

  recollirUsuari(){
    this.usuariService.getUsuaris().subscribe(
      (result) =>{
        this.usuaris = result;
        console.log(this.usuaris);
        for (let i=0;i<this.usuaris.length;i++){
          if (this.usuaris[i].email == this.form.email){
            this.usuari = this.usuaris[i];
            localStorage.setItem('idUsuari',this.usuari.id);
            localStorage.setItem('tipoUsuario',this.usuari.tipoUsuario);
           // this.tipoUsuario = localStorage.getItem('tipoUsuario');
           // this.idUsuari = localStorage.getItem('idUsuari');
            //console.log("Tipo usuario en login", this.tipoUsuario);
           // console.log("el id en login", this.idUsuari);
          }
        }
      },
      (error) =>{
        console.log(error);
      }
    );



    // this.usuariService.getUserByEmail(this.form.email)
    // .subscribe(
    //   (result)=>{
    //     console.log("Result ",result);
    //     this.usuari = result;
    //     console.log("Usuari: ", this.usuari);
    //     console.log("Usuari por id: ", this.usuari.id);
    //     localStorage.setItem('idUsuari', this.usuari.id);
    //     console.log("A ver que devuelve: ", localStorage.getItem('idUsuari'));
    //   },
    //   (error)=>{
    //     console.log("El error del usuario ",error);
    //   }
    // );
  }

  showModal() {
    Swal.fire({
      text: 'Usuari o contrasenya no correctes',
      icon: 'warning',
      showCloseButton: true,
      confirmButtonText: 'Ok',
    });
  }

}
