
package br.com.descompila.Models;

import java.util.HashMap;
import java.util.Map;

public class EstatisticasExpo {
    private int totalParticipacoes;
    private int acertosGerais;
    private int errosGerais;
    private Map<Integer, Double> acertosPorQuestao;
    private Map<String, Double> distribuicaoAvaliacoes;

    public EstatisticasExpo(int totalParticipacoes, int acertosGerais, int errosGerais, Map<Integer, Double> acertosPorQuestao, Map<String, Double> distribuicaoAvaliacoes) {
        this.totalParticipacoes = 0;
        this.acertosGerais = 0;
        this.errosGerais = 0;
        this.acertosPorQuestao = new HashMap<>();
        this.distribuicaoAvaliacoes = new HashMap<>();
        
        this.distribuicaoAvaliacoes.put("BOM", 0.0);
        this.distribuicaoAvaliacoes.put("MEDIO", 0.0);
        this.distribuicaoAvaliacoes.put("RUIM", 0.0);
    }
    
    public void atualizarSessao(SessaoPsquisa sessao) {
        this.totalParticipacoes++;
        this.acertosGerais += sessao.getAcertos();
        this.errosGerais += sessao.getErros();
        
        Map<Questao, Integer> respostas = sessao.getRespostasUser();
        
        for(Map.Entry<Questao, Integer> entrada : respostas.entrySet()) {
            Questao q = entrada.getKey();
            int indiceEscolhido = entrada.getValue();
            
            if(q.isIsQuestao()) {
                String nota = q.getAlternativas().get(indiceEscolhido);
                double votosAtuais = distribuicaoAvaliacoes.getOrDefault(nota, 0.0);
                distribuicaoAvaliacoes.put(nota, votosAtuais + 1.0);
            } 
            else {
                if (q.verificarResp(indiceEscolhido)) {
                    int idQuestao = q.getId();
                    double acertosAtuais = acertosPorQuestao.getOrDefault(idQuestao, 0.0);
                    acertosPorQuestao.put(idQuestao, acertosAtuais + 1);
                }
            }
        }
    }
    
    public double gerarPorcentagem(int questaoId) {
        if (totalParticipacoes == 0) {
            return 0.0;
        }
        
        double totalAcertosQuestao = acertosPorQuestao.getOrDefault(questaoId, 0.0);
        return (totalAcertosQuestao / totalParticipacoes) * 100;
    }
    
    public String identificarAlertaAprendizado() {
        if (totalParticipacoes == 0) {
            return "Nenhum dado coletado ainda.";
        }
        
        StringBuilder alerta = new StringBuilder();
        
        for (Integer idQuestao : acertosPorQuestao.keySet()) {
            double porcentagem = gerarPorcentagem(idQuestao);
            
            if (porcentagem < 50.0) {
                alerta.append("-> ALERTA! A Questão [").append(idQuestao)
                      .append("] tem apenas ")
                      .append(String.format("%.1f", porcentagem))
                      .append("% de acertos.\n");
            }
        }
        
        if (alerta.length() == 0) {
            return "EXPOSIÇÃO BEM AVALIADA. Nenhuma questão abaixo de 50% de acertos.";
        }
        
        return alerta.toString();
    }

    public int getTotalParticipacoes() {
        return totalParticipacoes;
    }

    public int getAcertosGerais() {
        return acertosGerais;
    }

    public int getErrosGerais() {
        return errosGerais;
    }

    public Map<Integer, Double> getAcertosPorQuestao() {
        return acertosPorQuestao;
    }

    public Map<String, Double> getDistribuicaoAvaliacoes() {
        return distribuicaoAvaliacoes;
    }
    
    
}
