package br.com.cadastro.minsait.controller;

import br.com.cadastro.minsait.dtos.PessoaResponseDTO.PessoaResponseDTO;
import br.com.cadastro.minsait.dtos.pessoaRequestDTO.PessoaRequestDTO;
import br.com.cadastro.minsait.services.pessoaServiceIMPL.PessoaServiceIMPL;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/v1/pessoa")

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
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.BuscarPorId(id));
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


}


