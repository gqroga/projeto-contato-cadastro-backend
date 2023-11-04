package br.com.cadastro.minsait.services.contatoServiceIMPL;

import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;
import br.com.cadastro.minsait.exceptions.ContatoException;
import br.com.cadastro.minsait.model.ContatoModel;
import br.com.cadastro.minsait.repositories.ContatoRepository;
import br.com.cadastro.minsait.services.ContatoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class ContatoServiceIMPL implements ContatoService {

    @Autowired
    private final ContatoRepository contatoRepository;

    @Autowired
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public ContatoResponseDTO criarContato(ContatoRequestDTO contatoRequestDTO) {
        ContatoModel contatoModel = modelMapper.map(contatoRequestDTO, ContatoModel.class);
        contatoRepository.save(contatoModel);
        return modelMapper.map(contatoModel, ContatoResponseDTO.class);

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
