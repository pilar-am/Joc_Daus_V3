package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.domain.Usuari;

public interface UsuariRepository extends JpaRepository<Usuari, Integer>{
	Optional<Usuari> findOneByEmail(String email);
	
}
