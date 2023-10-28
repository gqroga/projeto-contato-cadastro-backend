package br.com.cadastro.minsait.dtos.pessoaRequestDTO;

import jakarta.validation.constraints.NotBlank;

public class PessoaRequestDTO {

    @NotBlank(message = "Preenchimento obrigatório.")
    private String nome;
    private String endereco;
    private String cep;
    private String cidade;
    private String uf;

    public PessoaRequestDTO() {
    }

    public PessoaRequestDTO(String nome, String endereco, String cep, String cidade, String uf) {
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}


