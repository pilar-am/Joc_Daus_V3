# JOC DE DAUS VERSIÓ 3

### API on es gestiona un joc de daus, amb usuaris i partides dels usuaris amb base de dades relacional(MySQL)<br>
### La seguretat s'implementa amb JSON Web Token.

#### L'API es consumeix en el front amb l'aplicació joc_daus_app, feta amb Angular
<br>

URLs:
http://localhost:9001/players  Un jugador es pot inscriure amb el seu nom o de forma anònima<br>
http://localhost:9001/players  Retorna la llista dels usuaris amb el ranking actualitzat<br>
http://localhost:9001/players/{id}/games  Un jugador/a específic realitza una tirada dels daus<br>
http://localhost:9001/players/{id}/games  Retorna el llistat de jugades per un jugador/a<br>
http://localhost:9001/players/{id}/games  Elimina les tirades del jugador/a<br>
http://localhost:9001/players/ranking     Retorna el ranking mig dels jugadors<br>
http://localhost:9001/players/ranking/loser Retorna el jugador/a amb pitjor percentatge d'èxit<br>
http://localhost:9001/players/ranking/winner Retorna el jugador/a  amb millor percentatge d’èxit<br>
http://localhost:9001/players/{id} Modifica el nom del jugador<br>


Instal.lació aplicació frontend:  npm install<br>
Execució aplicació frontend: ng serve -o
