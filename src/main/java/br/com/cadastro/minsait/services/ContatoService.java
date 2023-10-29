package br.com.cadastro.minsait.services;

import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;

import java.util.List;

public interface ContatoService {
    ContatoResponseDTO adicionarContato( Long idPessoa,ContatoRequestDTO contatoRequestDTO);

    List<ContatoResponseDTO> buscarContatosPorPessoa(Long idPessoa);

    ContatoResponseDTO buscarContatoPorId(Long id);

    ContatoResponseDTO atualizarContato(Long id, ContatoRequestDTO contatoRequestDTO);

    void deletar(Long id);
}
