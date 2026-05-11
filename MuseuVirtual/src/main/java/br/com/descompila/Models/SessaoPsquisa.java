
package br.com.descompila.Models;

import java.util.Map;

public class SessaoPsquisa {
    private Map<Questao, Integer> respostasUser;
    private int acertos;
    private int erros;
    private String notaExposicao;

    public SessaoPsquisa(Map<Questao, Integer> respostasUser, int acertos, int erros, String notaExposicao) {
        this.respostasUser = respostasUser;
        this.acertos = acertos;
        this.erros = erros;
        this.notaExposicao = notaExposicao;
    }
    
    public void registrarResposta(Questao questao, int opcaoEscolhida) {
        respostasUser.put(questao, opcaoEscolhida);
    }
    
    public void calcularScore() {
        this.acertos = 0;
        this.erros = 0;
        
        for (Map.Entry<Questao, Integer> entrada : respostasUser.entrySet()) {
            Questao questaoAtual = entrada.getKey();
            int indiceEscolhido = entrada.getValue();
            
            if (questaoAtual.isIsQuestao()) {
                this.notaExposicao = questaoAtual.getAlternativas().get(indiceEscolhido);
            }
            else {
                if (questaoAtual.verificarResp(indiceEscolhido)) {
                    this.acertos ++;
                }
                else {
                    this.erros ++;
                }
            }
        }
        
        
    }

    public Map<Questao, Integer> getRespostasUser() {
        return respostasUser;
    }

    public int getAcertos() {
        return acertos;
    }

    public int getErros() {
        return erros;
    }

    public String getNotaExposicao() {
        return notaExposicao;
    }
    
    
}
