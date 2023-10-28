package br.com.cadastro.minsait.model;

import br.com.cadastro.minsait.repositories.PessoaRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")

public class PessoaModel  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String nome;
    @Column(nullable = false)
    private String endereco;
    private String cep;
    private String cidade;
    private String uf;
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ContatoModel> contatos = new ArrayList<>();

    public PessoaModel () {

    }

    public PessoaModel(Long id, String nome, String endereco, String cep, String cidade, String uf, List<ContatoModel> contatos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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