import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {UsuariService} from '../../services/usuari.service'
import {Usuari} from '../../models/usuari'
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registre',
  templateUrl: './registre.component.html',
  styleUrls: ['./registre.component.css']
})
export class RegistreComponent implements OnInit {

  userForm: FormGroup;

  constructor(private userBuilder: FormBuilder, private router:Router, private usuariService:UsuariService) {
    this.userForm = this.userBuilder.group({
      nom: new FormControl(),
      password: new FormControl('', Validators.required),
      confPassword: new FormControl('', Validators.required),
      email: new FormControl('', (Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")))
    });
   }

  ngOnInit(): void {
  }



  registrarUsuari(){
    console.log("Valors usuari ", this.userForm.value);
    if(this.userForm.value.password != this.userForm.value.confPassword){
      alert("No coinciden las contraseñas");
    }else{
      this.usuariService.registreUser(this.userForm.value).subscribe(
        (result) => {
          console.log("Se ha registrado el usuario")
          //alert("Usuario registrado")
          this.showModal();
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 500);
          this.router.navigate(['/login']);
        },
        (error) =>{
          console.log("Error al registrarse", error);
          if(error.error == "Nom duplicat"){
            this.showModal2();
          }
          if(error.error == "Email duplicat"){
            this.showModal3();
          }

          console.log("Cojo el error ", error.error);
        }
      );

    }
  }


  // checkPassword2(){
  //   let password:any;
  //   let password2:any;
  //   password = document.getElementById("password");
  //   password2 = document.getElementById("password2");

  //   function validatePassword()  {
  //     if(password.value =='')
  //     {
  //       password2.setCustomValidity("Passwords Don't Match");
  //     }
  //     else if(password.value != password2.value)
  //     {
  //       password.setCustomValidity("Passwords must not be empty");
  //     }
  //     else
  //     {
  //       password.setCustomValidity('');
  //     }
  //   }
  //   password.onchange = validatePassword;
  //   password2.onkeyup = validatePassword;
  // }




  // checkPassword(){
  //   let password:any;
  //   let password2:any;

  //   password = document.getElementById('password');
  //   password2 = document.getElementById('password2');

  //   password.onchange = password2.onkeyup = passwordMatch;

  //   function passwordMatch() {
  //       if(password.value !== password2.value)
  //           password2.setCustomValidity('Las contraseñas no coinciden.');
  //       else
  //           password2.setCustomValidity('');
  //   }
  // }

  showModal() {
    Swal.fire({
      text: 'Usuari registrat amb éxit',
      icon: 'success',
      showCloseButton: true,
      confirmButtonText: 'Ok',
    });
  }

  showModal2() {
    Swal.fire({
      text: 'El nom està duplicat',
      icon: 'warning',
      showCloseButton: true,
      confirmButtonText: 'Ok',
    });
  }

  showModal3() {
    Swal.fire({
      text: 'El email està duplicat',
      icon: 'warning',
      showCloseButton: true,
      confirmButtonText: 'Ok',
    });
  }
}
