package br.com.descompila.Models;

import java.util.List;


public class Questao {
    private int id;
    private String enuncidado;
    private List<String> alternativas;
    private int indiceRespCorreta;
    private boolean isQuestao;

    public Questao(int id, String enuncidado, List<String> alternativas, int indiceRespCorreta, boolean isQuestao) {
        this.id = id;
        this.enuncidado = enuncidado;
        this.alternativas = alternativas;
        this.indiceRespCorreta = indiceRespCorreta;
        this.isQuestao = isQuestao;
    }

    public boolean verificarResp(int indice) {
        if (isQuestao) {
            return true;
        }
        return this.indiceRespCorreta == indice;
    }

    public int getId() {
        return id;
    }

    public String getEnuncidado() {
        return enuncidado;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public int getIndiceRespCorreta() {
        return indiceRespCorreta;
    }

    public boolean isIsQuestao() {
        return isQuestao;
    }
    
    
}
