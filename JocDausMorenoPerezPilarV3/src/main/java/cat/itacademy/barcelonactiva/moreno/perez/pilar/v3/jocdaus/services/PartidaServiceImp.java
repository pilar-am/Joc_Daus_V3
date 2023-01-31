package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.domain.Partida;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.domain.Usuari;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.repository.PartidaRepository;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.repository.UsuariRepository;

@Service
public class PartidaServiceImp implements PartidaService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PartidaRepository partidaRepository;
	
	@Autowired
	UsuariRepository usuariRepository;

	@Override
	public void savePartidaDTO(PartidaDTO partidaDTO) {
		
		int tirada1 = partidaDTO.numAleatori();
		int tirada2 = partidaDTO.numAleatori();
		
		partidaDTO.setTirada1(tirada1);
		partidaDTO.setTirada2(tirada2);
		partidaDTO.setPuntuacio(tirada1+tirada2);
		
		Partida partida = new Partida();
		
		partida.setTirada1(partidaDTO.getTirada1());
		partida.setTirada2(partidaDTO.getTirada2());
		partida.setPuntuacio(partidaDTO.getPuntuacio());
		
		Usuari usuari = modelMapper.map(partidaDTO.getUsuariDTO(), Usuari.class);
		
		partida.setUsuari(usuari);
				
		modelMapper.map(partidaDTO.getUsuariDTO(), Usuari.class);
		partidaRepository.save(partida);
		
	}

	//Retorna las partidas de un usuario según su id
	@Override
	public List<PartidaDTO> llistaPartidesByUsuari(int id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		List<PartidaDTO> partides = usuariDTO.getPartidas();
		
		return partides;
	}

	//Elimina las partidas de un usuario según su id
	@Override
	public boolean deletePartidesByUsuari(int id) {
		List<Partida> partides = partidaRepository.findAll();
		boolean hayId = false;
		for(Partida p: partides) {
			if(p.getUsuari().getId() == id) {
				hayId=true;
				partidaRepository.delete(p);
			}
		}
		return hayId;
	}
	
	
	
}
