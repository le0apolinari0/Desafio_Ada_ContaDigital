package br.com.desafioAda.repository;

import br.com.desafioAda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpfCnpj(String cpfCnpj);

    Optional<Usuario> findByUuid(String uuid);

    Boolean existsByEmail(String email);

    Boolean existsByCpfCnpj(String cpfCnpj);

    Optional<Usuario> findByEmailAndAtivoTrue(String email);
}
