package br.com.cadastro.minsait.dtos.PessoaResponseDTO;

import br.com.cadastro.minsait.model.ContatoModel;

import java.util.List;

public class PessoaResponseDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String cidade;
    private String uf;
    private List<ContatoModel> contatos;

    public PessoaResponseDTO() {
    }

    public PessoaResponseDTO(Long id, String nome, String endereco, String cidade, String uf, List<ContatoModel> contatos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.contatos = contatos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<ContatoModel> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoModel> contatos) {
        this.contatos = contatos;
    }
}
