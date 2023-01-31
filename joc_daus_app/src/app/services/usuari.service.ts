import { Credenciales } from './../models/credenciales';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHeaders, HttpClient, HttpResponse } from '@angular/common/http';
import {variables} from '../common/variables'
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuariService {

  constructor(private http: HttpClient) { }

  //Rutas
  private API_REGISTRO = `${variables.URL_SPRING}/players/registre`;
  private API_LOGIN = `${variables.URL_SPRING}/players/login`;
  private API_LOGOUT = `${variables.URL_SPRING}/logout`;

  private API_USUARI_BY_EMAIL = `${variables.URL_SPRING}/players/`;

  public registroUser(usuari: any){
    console.log('Estoy en el servicio registrar usuario',usuari)
    return this.http.post<any>(this.API_REGISTRO, usuari, { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem('token')}` }) })
  }

  //RUTAS PRUEBA SIN JWT
  private API = `${variables.URL_SPRING}/players`;


  public registreUser(usuari:any){
    console.log("Registre usuari: ", usuari)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.post<any>(this.API_REGISTRO, usuari, { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem('token')}` })  });
  }

  public login(credencials: Credenciales){
    console.log('Estoy en el servicio, login()', credencials)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      //'Authorization': `Bearer ${localStorage.getItem('token')}`
    });

    return this.http.post(this.API_LOGIN, credencials,{headers:headers})
  }

  public getToken(){
    return localStorage.getItem('token');
  }

  public getUserByEmail(email: String) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.get(this.API_USUARI_BY_EMAIL + email, { headers: headers });
  }
  public getUserById(id: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.get(this.API_USUARI_BY_EMAIL + id, { headers: headers });
  }


  public getUsuaris(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.get(this.API, {headers:headers})
  }

  public getUsuarisRanking(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.get(this.API+"/ranking", {headers:headers})
  }

  public updateUser(id:any, usuari:any){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.put(this.API_USUARI_BY_EMAIL + id, usuari, {headers:headers})
  }

  public deletePartides(id:any){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.delete(this.API_USUARI_BY_EMAIL + id + "/games", {headers:headers})
  }

  public getLoser(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.get(this.API_USUARI_BY_EMAIL+"ranking/loser",{headers:headers})
  }

  public getWinner(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.get(this.API_USUARI_BY_EMAIL+"ranking/winner",{headers:headers})

  }
}
