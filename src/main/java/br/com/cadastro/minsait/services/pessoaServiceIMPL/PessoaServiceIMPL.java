package br.com.cadastro.minsait.services.pessoaServiceIMPL;

import br.com.cadastro.minsait.dtos.PessoaResponseDTO.PessoaResponseDTO;
import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;
import br.com.cadastro.minsait.dtos.malaDiretaResponseDTO.MalaDiretaResponseDTO;
import br.com.cadastro.minsait.dtos.pessoaRequestDTO.PessoaRequestDTO;
import br.com.cadastro.minsait.exceptions.ContatoException;
import br.com.cadastro.minsait.exceptions.PessoaException;
import br.com.cadastro.minsait.model.ContatoModel;
import br.com.cadastro.minsait.model.PessoaModel;
import br.com.cadastro.minsait.repositories.ContatoRepository;
import br.com.cadastro.minsait.repositories.PessoaRepository;
import br.com.cadastro.minsait.services.ContatoService;
import br.com.cadastro.minsait.services.PessoaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PessoaServiceIMPL implements PessoaService {

    @Autowired
    private final PessoaRepository pessoaRepository;

    @Autowired
    private final ContatoRepository contatoRepository;

    @Autowired
    private final ContatoService contatoService;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public PessoaResponseDTO criarPessa(PessoaRequestDTO pessoaRequestDTO) {
        PessoaModel pessoaModel = pessoaRepository.save(modelMapper.map(pessoaRequestDTO, PessoaModel.class));
        return modelMapper.map(pessoaModel, PessoaResponseDTO.class);
    }

    @Override
    public PessoaResponseDTO buscarPorId(Long id) {
        Optional<PessoaModel> pessoaModel = pessoaRepository.findById(id);

        if (pessoaModel.isEmpty()){
            throw new PessoaException("Pessoa não encontrada");
        }

        return modelMapper.map(pessoaModel, PessoaResponseDTO.class);
    }

    @Override
    public List<PessoaResponseDTO> buscarPessoas() {
        return pessoaRepository.findAll().stream().map(pessoaModel -> modelMapper.map(pessoaModel, PessoaResponseDTO.class)).toList();
    }
    @Transactional
    @Override
    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PessoaResponseDTO editarPessoa(Long id, PessoaRequestDTO pessoaRequestDTO) {
        PessoaModel pessoaModel = modelMapper.map(buscarPorId(id), PessoaModel.class);
        atualizandoPessoa(pessoaRequestDTO, pessoaModel);
        pessoaRepository.save(pessoaModel);
        return modelMapper.map(pessoaModel, PessoaResponseDTO.class);
    }

    private void atualizandoPessoa(PessoaRequestDTO pessoaRequestDTO, PessoaModel pessoaModel){
        if (!pessoaRequestDTO.getNome().isBlank()){
            pessoaModel.setNome(pessoaRequestDTO.getNome());
        }
            pessoaModel.setEndereco(pessoaRequestDTO.getEndereco());
            pessoaModel.setCep(pessoaRequestDTO.getCep());
            pessoaModel.setCidade(pessoaRequestDTO.getCidade());
            pessoaModel.setUf(pessoaRequestDTO.getUf());
    }

    public PessoaResponseDTO adicionarContato(Long idPessoa, ContatoRequestDTO contatoRequestDTO) {
        PessoaModel pessoaModel = modelMapper.map(buscarPorId(idPessoa), PessoaModel.class);

        ContatoModel contatoModel = modelMapper.map(contatoService.criarContato(contatoRequestDTO), ContatoModel.class);

        contatoModel.setPessoa(pessoaModel);
        contatoRepository.save(contatoModel);

        pessoaModel.getContatos().add(contatoModel);
        return modelMapper.map(pessoaModel, PessoaResponseDTO.class);
    }

    @Override
    public List<ContatoResponseDTO> buscarContatosPorPessoa(Long idPessoa) {
        PessoaModel pessoaModel = modelMapper.map(buscarPorId(idPessoa),PessoaModel.class);

        if (pessoaModel.getContatos().isEmpty()) {
            throw new ContatoException("Não existe contato cadastrado.");
        }

        return pessoaModel.getContatos().stream().map(contato -> modelMapper.map(contato, ContatoResponseDTO.class)).toList();
    }

    @Override
    public MalaDiretaResponseDTO buscarMalaDireta(Long id){
        PessoaModel pessoaModel = modelMapper.map(buscarPorId(id),PessoaModel.class);
        MalaDiretaResponseDTO malaDiretaResponseDTO = new MalaDiretaResponseDTO(pessoaModel.getId(),pessoaModel.getNome(),
                pessoaModel.getEndereco() + " - CEP: " + pessoaModel.getCep() + " - " + pessoaModel.getCidade() + "/" + pessoaModel.getUf());
        return malaDiretaResponseDTO;
    }
}