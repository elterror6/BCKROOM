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

import java.util.Objects;

import es.uclm.bckroom.config.TamanioColumnas;

@Entity
@Table(
		indexes={
			@Index(columnList="email")
		}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable=false, length = TamanioColumnas.EMAIL)
	private String email;
	
	@Column(nullable=false, length = TamanioColumnas.USERNAME)
	private String username;
	
	@Column(nullable=false, length = TamanioColumnas.PASSWORD)
	private String passwd;
	
	@Column(nullable=false, length = TamanioColumnas.NOMBRE)
	private String nombre;
	
	@Column(nullable=false, length = TamanioColumnas.APELLIDOS)
	private String primerApellido;
	
	@Column(nullable=false, length = TamanioColumnas.APELLIDOS)
	private String segundoApellido;
	
	@Embedded
	private DireccionUsuario direccion;
	
	

	public Usuario() {
		super();
	}
	
	public Usuario(Usuario usuario) {
		this.id=usuario.id;
		this.email=usuario.email;
		this.nombre=usuario.nombre;
		this.primerApellido=usuario.primerApellido;
		this.segundoApellido=usuario.segundoApellido;
		this.passwd=usuario.passwd;
		this.username=usuario.username;
	}

	public Usuario(String email, String username, String passwd, String nombre, String primerApellido,
			String segundoApellido, DireccionUsuario direccion) {
		super();
		this.email = email;
		this.username = username;
		this.passwd = passwd;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.direccion = direccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public DireccionUsuario getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionUsuario direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", username=" + username + ", passwd=" + passwd + ", nombre="
				+ nombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido
				+ ", direccion=" + direccion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nombre, primerApellido, segundoApellido, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(primerApellido, other.primerApellido)
				&& Objects.equals(segundoApellido, other.segundoApellido) && Objects.equals(username, other.username);
	}
	
	
}
