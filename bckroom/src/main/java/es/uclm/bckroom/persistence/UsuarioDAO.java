package es.uclm.bckroom.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.bckroom.business.domain.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario,Long>{
	Optional<Usuario> findByUsername(String username);
}
