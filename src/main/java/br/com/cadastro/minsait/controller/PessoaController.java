package br.com.cadastro.minsait.controller;

import br.com.cadastro.minsait.dtos.PessoaResponseDTO.PessoaResponseDTO;
import br.com.cadastro.minsait.dtos.contatoRequestDTO.ContatoRequestDTO;
import br.com.cadastro.minsait.dtos.contatoResponseDTO.ContatoResponseDTO;
import br.com.cadastro.minsait.dtos.malaDiretaResponseDTO.MalaDiretaResponseDTO;
import br.com.cadastro.minsait.dtos.pessoaRequestDTO.PessoaRequestDTO;
import br.com.cadastro.minsait.services.pessoaServiceIMPL.PessoaServiceIMPL;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/pessoas")

public class PessoaController {

    final PessoaServiceIMPL pessoaService;

    public PessoaController(PessoaServiceIMPL pessoaService) {
        this.pessoaService = pessoaService;
    }


    @PostMapping()
    @Operation(summary = "Criar pessoa")
    public ResponseEntity<PessoaResponseDTO> SalvarPessoa(@RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.criarPessa(pessoaRequestDTO));

    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar pessoa por ID")
    public ResponseEntity<PessoaResponseDTO> BuscarPessoaPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> listarPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPessoas());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPessoa (@PathVariable(value = "id")Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaResponseDTO> editarPessoa(@PathVariable(value = "id")Long id, @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.editarPessoa(id, pessoaRequestDTO));
    }

    @PostMapping("{idPessoa}/contatos")
    public ResponseEntity<PessoaResponseDTO> adicionarContato(@PathVariable(value = "idPessoa") Long idPessoa, @RequestBody @Valid ContatoRequestDTO contatoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.adicionarContato(idPessoa, contatoRequestDTO));
    }
    @GetMapping("{idPessoa}/contatos")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatosPorPessoa (@PathVariable(value = "idPessoa") Long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarContatosPorPessoa(idPessoa));
    }

    @GetMapping("maladireta/{id}")
    @Operation(summary = "Buscar MalaDireta por ID de pessoa")
    public ResponseEntity<MalaDiretaResponseDTO> BuscarMaladaDireta(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarMalaDireta(id));
    }
}


