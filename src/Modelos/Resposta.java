package Modelos;

public class Resposta {
    protected int Questao;
    protected int indiceEscolhido;
    protected boolean acerto;

    public Resposta(int questao, int indiceEscolhido, boolean acerto) {
        Questao = questao;
        this.indiceEscolhido = indiceEscolhido;
        this.acerto = acerto;
    }

    public int getQuestao() {
        return Questao;
    }

    public boolean isAcerto() {
        return acerto;
    }
}
