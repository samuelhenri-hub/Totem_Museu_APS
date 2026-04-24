package Modelos;

public class Obra {
    protected int Id;
    protected String Titulo;
    protected String historico;
    protected String CaminhoImagem;

    public Obra(int id, String titulo, String historico, String caminhoImagem) {
        Id = id;
        Titulo = titulo;
        this.historico = historico;
        CaminhoImagem = caminhoImagem;
    }

    public int getId() {
        return Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getHistorico() {
        return historico;
    }

    public String getCaminhoImagem() {
        return CaminhoImagem;
    }
}
