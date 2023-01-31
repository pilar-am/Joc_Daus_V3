package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.config;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

		//Estos dos valores mejor en properties
		//Clave secreta
		private final static String ACCESS_TOKEN_SECRET="lacontraseñaqueyomeinvente1234skdieo@@eirnwalso596079@#";
		//Tiempo de vida util del token  30 dias, en segundos
		private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2592000L;
		
//		private static String jwt_secret;
//		private static int jwtExpiration;
//
//		@Value("${jwt.secret}")
//		public void setSecret(String jwt_secret) {
//			TokenUtils.jwt_secret=jwt_secret;
//		}
//		
//		@Value("${jwt.expiration}")
//		public void setJwtExpirationInMs(int jwtExpiration) {
//			TokenUtils.jwtExpiration = jwtExpiration;
//		}
		
		//CREA EL TOKEN
		public static String createToken(String nom, String email) {
			//pasar de segundos a milisegundos
			long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
			
			//Suma la fecha actual con el tiempo de duracion del token
			Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
			
			Map<String, Object> extra = new HashMap<>();
			
			extra.put("password", nom);
			
			return Jwts.builder()
					.setSubject(email)
					.setExpiration(expirationDate)
					.addClaims(extra)
					.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
					.compact();
		}
		
		//Envia el token  se supone que se autentica con el email
		public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
			
			try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email = claims.getSubject();
			
			return new UsernamePasswordAuthenticationToken(email, null, java.util.Collections.emptyList());
			
			}catch(JwtException e) {
				return null;
			}
		}
		
}
