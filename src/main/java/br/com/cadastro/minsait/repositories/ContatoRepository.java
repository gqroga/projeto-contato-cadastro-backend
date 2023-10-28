package br.com.cadastro.minsait.repositories;

import br.com.cadastro.minsait.model.ContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository <ContatoModel, Long>{
}
