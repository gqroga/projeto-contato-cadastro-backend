package br.com.cadastro.minsait.repositories;


import br.com.cadastro.minsait.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
}


