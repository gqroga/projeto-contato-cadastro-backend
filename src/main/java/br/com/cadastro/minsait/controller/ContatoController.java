package br.com.cadastro.minsait.controller;

import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;
import br.com.cadastro.minsait.services.contatoServiceIMPL.ContatoServiceIMPL;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/contatos")

public class ContatoController {

    private final ContatoServiceIMPL contatoService;

    public ContatoController(ContatoServiceIMPL contatoService) {
        this.contatoService = contatoService;
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um Contato existente")
    public ResponseEntity<ContatoResponseDTO> atualizarContato (@PathVariable(value = "id")Long id, @RequestBody ContatoRequestDTO contatoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.atualizarContato(id, contatoRequestDTO));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove um Contato por ID")
    public ResponseEntity<ContatoResponseDTO> deletar (@PathVariable(value = "id") Long id) {
        contatoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna os dados de um Contato por ID")
    public ResponseEntity<ContatoResponseDTO> buscarContatoPorId (@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.buscarContatoPorId(id));
    }

}
