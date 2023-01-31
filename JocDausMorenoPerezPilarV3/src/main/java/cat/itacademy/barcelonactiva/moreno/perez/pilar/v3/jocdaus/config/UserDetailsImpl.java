package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.domain.Usuari;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{
	
	private final Usuari usuari;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return usuari.getPassword();
	}

	@Override
	public String getUsername() {
		return usuari.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	//Método para poder saber el nombre
	public String getNom() {
		return usuari.getNom();
	}
	
	
	

	
}
