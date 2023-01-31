import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHeaders, HttpClient, HttpResponse } from '@angular/common/http';
import {variables} from '../common/variables'

@Injectable({
  providedIn: 'root'
})
export class PartidaService {

  constructor(private http: HttpClient) { }

  private API_PARTIDA = `${variables.URL_SPRING}/players/`;
  //private API_PARTIDES = `${variables.URL_SPRING}/players/`;


  public nuevaPartida(partida:any){
    console.log("En servicio partida ",partida);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });

    return this.http.post(this.API_PARTIDA + localStorage.getItem('idUsuari') + "/games", partida, { headers: headers });
  }

  public mostrarPartidesUsuari(idUser:any){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.get<any>(this.API_PARTIDA + idUser + "/games", { headers: headers });

  }
}
