package cat.itacademy.barcelonactiva.moreno.perez.pilar.v3.jocdaus.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuaris")//(name = "usuaris", uniqueConstraints = {@UniqueConstraint(columnNames = {"nom"})})   Nom únic
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuari {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@Column(unique=true)
	private String nom;
	
//	private static int anonim = 0;
//	private int numAnonim;
	
	//**** PARA AUTENTICACION ******
		private String email;
		private String password;
		private int tipoUsuario;
			
//		public Usuari(String nom, String email, String password) {
//			this.nom = nom;
//			this.email = email;
//			this.password = password;
//		}

	//******************************
	
	
	private LocalDate dataRegistre = LocalDate.now();
	
	
	@OneToMany(mappedBy = "usuari", cascade = CascadeType.ALL)
	private List<Partida> partidas;

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
		for(Partida p: partidas) {
			p.setUsuari(this);
		}
	}

//	public Usuari() {
//		this.anonim++;
//		this.numAnonim=this.anonim;
//	}
	
	
}
