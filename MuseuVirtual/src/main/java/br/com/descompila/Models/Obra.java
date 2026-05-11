package br.com.descompila.Models;

public class Obra {
    private String titulo;
    private String descricao;
    private String caminhoImagem;

    public Obra(String titulo, String descricao, String caminhoImagem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.caminhoImagem = caminhoImagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }
}
