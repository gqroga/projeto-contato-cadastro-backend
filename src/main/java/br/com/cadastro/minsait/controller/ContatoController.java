package br.com.cadastro.minsait.controller;

import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;
import br.com.cadastro.minsait.services.contatoServiceIMPL.ContatoServiceIMPL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/contatos")

public class ContatoController {

    public ContatoController(ContatoServiceIMPL contatoService) {
        this.contatoService = contatoService;
    }

    private final ContatoServiceIMPL contatoService;

    @GetMapping("{id}")
    public ResponseEntity<ContatoResponseDTO> buscarContatoPorId (@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.buscarContatoPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ContatoResponseDTO> atualizarContato (@PathVariable(value = "id")Long id, @RequestBody ContatoRequestDTO contatoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.atualizarContato(id, contatoRequestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ContatoResponseDTO> deletar (@PathVariable(value = "id") Long id) {
        contatoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
