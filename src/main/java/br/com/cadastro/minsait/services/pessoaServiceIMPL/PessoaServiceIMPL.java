package br.com.cadastro.minsait.services.pessoaServiceIMPL;

import br.com.cadastro.minsait.dtos.PessoaResponseDTO.PessoaResponseDTO;
import br.com.cadastro.minsait.dtos.pessoaRequestDTO.PessoaRequestDTO;
import br.com.cadastro.minsait.exceptions.PessoaException;
import br.com.cadastro.minsait.model.PessoaModel;
import br.com.cadastro.minsait.repositories.PessoaRepository;
import br.com.cadastro.minsait.services.PessoaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PessoaServiceIMPL implements PessoaService {


     private final PessoaRepository pessoaRepository;

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
}
