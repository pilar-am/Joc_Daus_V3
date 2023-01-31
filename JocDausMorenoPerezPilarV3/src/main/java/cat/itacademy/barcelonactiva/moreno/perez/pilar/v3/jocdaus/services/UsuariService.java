package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.services;

import java.util.List;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.dto.UsuariDTO;

public interface UsuariService {


	List<UsuariDTO> llistaUsuaris();
	UsuariDTO getUsuariById(Integer id);
	void saveUsuariDTO(UsuariDTO usuariDTO);
	UsuariDTO updateUsuariDTO(UsuariDTO usuariDTO, int id);
	List<UsuariDTO> usuarisAmbRanking();
	UsuariDTO getLoser();
	UsuariDTO getWinner();
	
	UsuariDTO getUsuariByEmail(String email);
}
