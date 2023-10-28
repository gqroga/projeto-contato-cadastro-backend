package br.com.cadastro.minsait.dtos.contatoResponseDTO;

public class ContatoResponseDTO {
    private Long id;
    private Integer tipoContato;
    private String contato;

    public ContatoResponseDTO() {
    }

    public ContatoResponseDTO(Long id, Integer tipoContato, String contato) {
        this.id = id;
        this.tipoContato = tipoContato;
        this.contato = contato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(Integer tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}


