package br.com.perfilcar.perfil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.perfilcar.perfil.models.Perfil;
import br.com.perfilcar.perfil.models.Usuario;


public interface UsuarioRepository  extends JpaRepository<Usuario,Long> {

	Usuario findById(long idUsuario);
}
