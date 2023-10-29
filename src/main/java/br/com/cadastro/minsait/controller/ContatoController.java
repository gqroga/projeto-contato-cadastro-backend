package br.com.cadastro.minsait.controller;

import br.com.cadastro.minsait.services.ContatoService;
import br.com.cadastro.minsait.services.contatoServicesIMPL.ContatoServiceIMPL;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/contato")

public class ContatoController {

    final ContatoServiceIMPL contatoService;

    public ContatoController(ContatoServiceIMPL contatoService) {this.contatoService = contatoService; }

    @PostMapping()
    @Operation(summary = "Criar contato")}
