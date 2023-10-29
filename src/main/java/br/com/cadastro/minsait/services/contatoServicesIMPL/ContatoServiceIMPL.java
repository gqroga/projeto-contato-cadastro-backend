package br.com.cadastro.minsait.services.contatoServicesIMPL;

import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;
import br.com.cadastro.minsait.exceptions.ContatoException;
import br.com.cadastro.minsait.model.ContatoModel;
import br.com.cadastro.minsait.model.PessoaModel;
import br.com.cadastro.minsait.repositories.ContatoRepository;
import br.com.cadastro.minsait.services.ContatoService;
import br.com.cadastro.minsait.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ContatoServiceIMPL implements ContatoService {


    private final ContatoRepository contatoRepository;

    private final PessoaService pessoaService;

    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public ContatoResponseDTO adicionarContato(Long idPessoa, ContatoRequestDTO contatoRequestDTO) {
        PessoaModel pessoaModel = modelMapper.map(pessoaService.buscarPorId(idPessoa),PessoaModel.class);

        ContatoModel contatoModel = modelMapper.map(contatoRequestDTO, ContatoModel.class);

        contatoModel.setPessoa(pessoaModel);

        contatoRepository.save(contatoModel);

        return modelMapper.map(contatoModel, ContatoResponseDTO.class);

    }

    @Override
    public List<ContatoResponseDTO> buscarContatosPorPessoa(Long idPessoa) {
        PessoaModel pessoaModel = modelMapper.map(pessoaService.buscarPorId(idPessoa),PessoaModel.class);

        if (pessoaModel.getContatos().isEmpty()) {
            throw new ContatoException("Não existe contato cadastrado.");
        }

        return pessoaModel.getContatos().stream().map(contato -> modelMapper.map(contato, ContatoResponseDTO.class)).toList();
    }

    @Override
    public ContatoResponseDTO buscarContatoPorId(Long id) {
        Optional<ContatoModel> contatoModel = contatoRepository.findById(id);

        if (contatoModel.isEmpty()) {
            throw new ContatoException("Nenhum contato para esse Id");
        }

        return modelMapper.map(contatoModel, ContatoResponseDTO.class);
    }

    @Override
    public ContatoResponseDTO atualizarContato(Long id, ContatoRequestDTO contatoRequestDTO) {
        ContatoModel contatoModel = modelMapper.map(buscarContatoPorId(id), ContatoModel.class);
        atualizarContato(contatoRequestDTO, contatoModel);
        contatoRepository.save(contatoModel);
        return modelMapper.map(contatoModel, ContatoResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        contatoRepository.deleteById(id);
    }

    private void atualizarContato(ContatoRequestDTO contatoRequestDTO, ContatoModel contatoModel) {
        contatoModel.setContato(contatoRequestDTO.getContato());
        contatoModel.setTipoContato(contatoRequestDTO.getTipoContato());

    }


}
