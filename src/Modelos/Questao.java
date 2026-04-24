package Modelos;

import java.util.List;

public class Questao {
    protected int id;
    protected String enunciado;
    protected List<String> alternativas;
    protected int indiceCorreta;

    public Questao(int id, String enunciado, List<String> alternativas, int indiceCorreta) {
        this.id = id;
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.indiceCorreta = indiceCorreta;
    }

    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public boolean isCorreta(int indiceEscolhido) {
        if (indiceEscolhido == this.indiceCorreta) {
            return true;
        } else {
            return false;
        }
    }
}
