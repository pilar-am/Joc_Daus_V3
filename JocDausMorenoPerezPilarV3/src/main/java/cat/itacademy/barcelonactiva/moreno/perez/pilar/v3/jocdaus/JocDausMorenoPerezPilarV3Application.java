package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.config.WebSecurityConfig;

@SpringBootApplication
public class JocDausMorenoPerezPilarV3Application {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/players/**").allowedOrigins("*");
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JocDausMorenoPerezPilarV3Application.class, args);
	
		//System.out.println("Contraseña " + WebSecurityConfig.passwordEncoder().encode("admin"));
		
		//Registro funciona asi  http://localhost:8080/api/add
		
		//login  http://localhost:8080/login
		
		//get usuario por id   http://localhost:8080/api/usuarios/4
		
		
		//http://localhost:9001/v3/api-docs  para el json de swagger
		//http://localhost:9001/swagger-ui/index.html  para la interfaz
		
	}

}
