package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.services.UsuariService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class UsuariController {

	@Autowired
	UsuariService usuariService;
	
	//GET: Retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits. 
	@GetMapping("/players")
	public ResponseEntity<List<UsuariDTO>> getAllUsers(){
		try {
			List<UsuariDTO> usuaris = usuariService.usuarisAmbRanking();
			if(usuaris.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<List<UsuariDTO>>(usuaris,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET: Retorna un jugador segons el seu email
	@GetMapping("/players/{email}")
	public ResponseEntity<UsuariDTO> jugadorSegonsEmail(@PathVariable("email") String email){
		try {
			UsuariDTO usuariDTO = usuariService.getUsuariByEmail(email);
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	//GET: Retorna jugador segons el seu id
	@GetMapping("/players/{id}")
	public ResponseEntity<UsuariDTO> getUsuariById(@PathVariable("id") Integer id){
		try {
			UsuariDTO usuariDTO = usuariService.getUsuariById(id);
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	
	//POST: Crea un jugador/a. 
	@PostMapping("/players/registre")
	public ResponseEntity<?> crearUsuari(@RequestBody UsuariDTO usuariDTO){
		try {
			if(usuariDTO.getNom() == null) {
				usuariDTO.setNom("ANÒNIM" + usuariDTO.getNumAnonim());
			}
			boolean repetit = false;
			boolean repetit2 = false;
			List<UsuariDTO> usuaris = usuariService.llistaUsuaris();
			for(UsuariDTO u:usuaris) {
				if(u.getNom().equals(usuariDTO.getNom()) && !u.getNom().equals("ANÒNIM")) {
					repetit=true;
				}
			}
			for(UsuariDTO u:usuaris) {
				if(u.getEmail().equals(usuariDTO.getEmail())) {
					repetit2=true;
				}
			}
			if(repetit) {
				return new ResponseEntity<>("Nom duplicat", HttpStatus.INTERNAL_SERVER_ERROR);
			}else if(repetit2) {
				return new ResponseEntity<>("Email duplicat", HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				usuariService.saveUsuariDTO(usuariDTO);
				return new ResponseEntity<>(usuariDTO, HttpStatus.CREATED);
			}
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//PUT: Modifica el nom del jugador/a.
	@PutMapping("/players/{id}")
	public ResponseEntity<UsuariDTO> updateUsuari(@PathVariable("id") Integer id, @RequestBody UsuariDTO usuariDTO){
		try {
			UsuariDTO usuari =  usuariService.updateUsuariDTO(usuariDTO, id);
			return new ResponseEntity<>(usuari, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	List<String> rankingUsuaris = new ArrayList<>();
	
	//GET: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits. 
	@GetMapping("/players/ranking")
	public ResponseEntity<List<UsuariDTO>> retornaRanking(){
		try {
			List<UsuariDTO> usuaris = usuariService.usuarisAmbRanking();
			if(usuaris.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				Collections.sort(usuaris);
				//rankingUsuaris.clear();
				for(UsuariDTO u:usuaris) {
					rankingUsuaris.add(u.getNom() + ": " + u.getRanking() + "%");
				}
				return new ResponseEntity<>(usuaris, HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
				
	}
	//GET: Retorna el jugador/a  amb pitjor percentatge d’èxit.  
	@GetMapping("/players/ranking/loser")
	public ResponseEntity<UsuariDTO> getLoser(){
		try {
			UsuariDTO usuariDTO = usuariService.getLoser();
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	//GET: Retorna el jugador/a  amb millor percentatge d’èxit.  
	@GetMapping("/players/ranking/winner")
	public ResponseEntity<UsuariDTO> getWinner(){
		try {
			UsuariDTO usuariDTO = usuariService.getWinner();
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	
	
}
