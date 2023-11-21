package br.fepi.socialbooks.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.fepi.socialbooks.domain.Comentario;


public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    
}

