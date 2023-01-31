package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.services;

import java.util.List;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.dto.PartidaDTO;

public interface PartidaService {

	void savePartidaDTO(PartidaDTO partidaDTO);
	List<PartidaDTO> llistaPartidesByUsuari(int id);
	public boolean deletePartidesByUsuari(int id);
}
