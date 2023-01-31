package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.domain.Usuari;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.repository.UsuariRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuariRepository usuariRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuari usuari = usuariRepository.findOneByEmail(email).orElseThrow(()-> new UsernameNotFoundException("L'usuari no existeix"));
		
		return new UserDetailsImpl(usuari);
	}
	
	
}
