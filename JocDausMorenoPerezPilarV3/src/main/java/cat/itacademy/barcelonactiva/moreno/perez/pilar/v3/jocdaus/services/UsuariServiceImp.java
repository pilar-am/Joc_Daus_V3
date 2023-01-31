package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.config.WebSecurityConfig;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.domain.Usuari;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.repository.UsuariRepository;

@Service
public class UsuariServiceImp implements UsuariService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UsuariRepository usuariRepository;
	
	@Autowired
	WebSecurityConfig webSecurityConfig;
	
	//Retorna todos los usuariosDTO
	@Override
	public List<UsuariDTO> llistaUsuaris() {
		return usuariRepository.findAll()
				.stream()
        		.map(this::convertEntityToDto)
        		.collect(Collectors.toList());
	}

	//Retorna un usuarioDTO según su id
	@Override
	public UsuariDTO getUsuariById(Integer id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		return usuariDTO;
	}

	//Usuario según su email
	@Override
	public UsuariDTO getUsuariByEmail(String email) {
		Optional<Usuari> usuari = usuariRepository.findOneByEmail(email);
		UsuariDTO usuariDTO = modelMapper.map(usuari.get(), UsuariDTO.class);
		return usuariDTO;
	}
	
	
	
	//Guarda un usuario
	@Override
	public void saveUsuariDTO(UsuariDTO usuariDTO) {
		Usuari usuari = new Usuari();
		usuari.setNom(usuariDTO.getNom());
		usuari.setEmail(usuariDTO.getEmail());
		
		String password = usuariDTO.getPassword();
		String passCodificado = webSecurityConfig.passwordEncoder().encode(password);
		usuari.setPassword(passCodificado);
		usuariRepository.save(usuari);
		
	}

	//Actualiza el usuario(nombre) y retorna usuariDTO
	@Override
	public UsuariDTO updateUsuariDTO(UsuariDTO usuariDTO, int id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		usuari.setNom(usuariDTO.getNom());
		Usuari usuariActualitzat = usuariRepository.save(usuari);
		return convertEntityToDto(usuariActualitzat);
	}

	//Retorna una lista de todos los usuarios con el ranking actualizado. Ranking sólo en DTO
	@Override
	public List<UsuariDTO> usuarisAmbRanking() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
		
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		return usuaris;
	}

	@Override
	public UsuariDTO getLoser() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
				
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		Collections.sort(usuaris);
		UsuariDTO usuariDTO = usuaris.get(usuaris.size()-1);
		return usuariDTO;
	}

	@Override
	public UsuariDTO getWinner() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
				
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		Collections.sort(usuaris);
		UsuariDTO usuariDTO = usuaris.get(0);
		return usuariDTO;
	}

	
	//De Entity a DTO
	private UsuariDTO convertEntityToDto(Usuari usuari) {
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		return usuariDTO;
	}

	
}
