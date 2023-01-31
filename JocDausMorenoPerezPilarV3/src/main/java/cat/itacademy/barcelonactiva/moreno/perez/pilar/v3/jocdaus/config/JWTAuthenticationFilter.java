package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.config;

import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		//Instancia de las credenciales
		AuthCredentials authCredentials = new AuthCredentials();
		
		//Nos envian credenciales en formato json, se convierte a objeto auth
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		} catch (java.io.IOException e) {	
			e.printStackTrace();
		}
		
		//Seguimos en proceso de autenticacion
		UsernamePasswordAuthenticationToken usernamePad = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(),
				authCredentials.getPassword(),
				Collections.emptyList()
		);
			
		return getAuthenticationManager().authenticate(usernamePad);
	}
	
	
	//Para el caso que la autenticacion haya sido exitosa
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 
											FilterChain chain,
											Authentication authResult) throws java.io.IOException, ServletException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		
		//Crear un token a partir de userdetailsimpl El getUsername es el correo que es lo que le hemos dicho en userdetailsimpl
		String token = TokenUtils.createToken(userDetails.getNom(), userDetails.getUsername());
		
		//necesitamos modificar la respuesta para adjuntar el token
		response.addHeader("Authorization", "Bearer " + token);
		response.getHeader(token);
		response.getWriter().append(token);  //Aqui me devuelve el token yujjuuu
		response.getWriter().flush();
		
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
