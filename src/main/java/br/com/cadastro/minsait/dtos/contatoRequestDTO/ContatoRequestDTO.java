package br.com.cadastro.minsait.dtos.contatoRequestDTO;

public class ContatoRequestDTO {

    private Integer tipoContato;
    private String contato;

    public ContatoRequestDTO() {
    }

    public ContatoRequestDTO(Integer tipoContato, String contato) {
        this.tipoContato = tipoContato;
        this.contato = contato;
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
