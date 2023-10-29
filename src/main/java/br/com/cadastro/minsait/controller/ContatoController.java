package br.com.cadastro.minsait.controller;

import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;
import br.com.cadastro.minsait.services.ContatoService;
import br.com.cadastro.minsait.services.contatoServicesIMPL.ContatoServiceIMPL;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class ContatoController {

    public ContatoController(ContatoServiceIMPL contatoService) {
        this.contatoService = contatoService;
    }

    private final ContatoServiceIMPL contatoService;

    @PostMapping("/pessoas/{idPessoa}/contatos")
    public ResponseEntity<ContatoResponseDTO> adicionarContato(@PathVariable(value = "idPessoa") Long idPessoa, @RequestBody @Valid ContatoRequestDTO contatoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.adicionarContato(idPessoa, contatoRequestDTO));
    }

    @GetMapping("/pessoas/{idPessoa}/contatos")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatosPorPessoa (@PathVariable(value = "idPessoa") Long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.buscarContatosPorPessoa(idPessoa));
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarContatoPorId (@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.buscarContatoPorId(id));
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<ContatoResponseDTO> atualizarContato (@PathVariable(value = "id")Long id, @RequestBody ContatoRequestDTO contatoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.atualizarContato(id, contatoRequestDTO));
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<ContatoResponseDTO> deletar (@PathVariable(value = "id") Long id) {
        contatoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
