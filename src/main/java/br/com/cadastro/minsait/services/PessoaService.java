package br.com.cadastro.minsait.services;

import br.com.cadastro.minsait.dtos.PessoaResponseDTO.PessoaResponseDTO;
import br.com.cadastro.minsait.dtos.pessoaRequestDTO.PessoaRequestDTO;

import java.util.List;

public interface PessoaService {
    PessoaResponseDTO criarPessa(PessoaRequestDTO pessoaRequestDTO);

    PessoaResponseDTO buscarPorId(Long id);

    List<PessoaResponseDTO> buscarPessoas();

    void deletar(Long id);

    PessoaResponseDTO editarPessoa(Long id, PessoaRequestDTO pessoaRequestDTO);
}
