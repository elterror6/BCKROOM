package es.uclm.bckroom.business.domain;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Embedded;

@Entity
@Table(
		indexes={
			@Index(columnList="username")
		}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable=false)
	private String username;
	
	@Column
	private String passwd;
	
	@Column
	private String nombre;
	
	@Column
	private String primerApellido;
	
	@Column
	private String segundoApellido;
	
	@Embedded
	private Direccion direccion;
	
	

	public Usuario() {
		super();
	}

	public Usuario(String username, String passwd, String nombre, String primerApellido,
			String segundoApellido, Direccion direccion) {
		super();
		this.username = username;
		this.passwd = passwd;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.direccion = direccion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public boolean checkPasswd(String posiblePasswd) {
		return this.passwd == posiblePasswd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
