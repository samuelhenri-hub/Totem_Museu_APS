package br.com.descompila.Controller;

import br.com.descompila.Models.EstatisticasExpo;
import br.com.descompila.Models.MemoriaDados;
import br.com.descompila.Models.SessaoPsquisa;
import br.com.descompila.View.TelaRelatorio;
import br.com.descompila.View.Inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class RelatorioController {

    private TelaRelatorio view;
    private EstatisticasExpo estatisticas;

    public RelatorioController() {
        this.view = new TelaRelatorio();
        
        this.estatisticas = processarDadosDaMemoria(); 
        
        preencherTelaComDados();
        configurarAcoes();
    }

    private EstatisticasExpo processarDadosDaMemoria() {
        EstatisticasExpo est = new EstatisticasExpo(0, 0, 0, null, null);
        
        for (SessaoPsquisa sessao : MemoriaDados.sessoesRealizadas) {
            est.atualizarSessao(sessao);
        }
        
        return est;
    }

    private void preencherTelaComDados() {
        String total = String.valueOf(estatisticas.getTotalParticipacoes());

        double mediaCalculada = 0;
        if (estatisticas.getTotalParticipacoes() > 0) {
            mediaCalculada = (double) estatisticas.getAcertosGerais() / estatisticas.getTotalParticipacoes();
        }
        String media = String.format("%.1f", mediaCalculada).replace(",", ".");

        String[] percentuaisQuestoes = new String[4];
        for (int i = 0; i < 4; i++) {
            int idQuestao = i + 1;
            double perc = estatisticas.gerarPorcentagem(idQuestao);
            percentuaisQuestoes[i] = String.format("%.0f%%", perc);
        }

        int votosBom = estatisticas.getDistribuicaoAvaliacoes().getOrDefault("BOM", 0.0).intValue();
        int votosMedio = estatisticas.getDistribuicaoAvaliacoes().getOrDefault("MEDIO", 0.0).intValue();
        int votosRuim = estatisticas.getDistribuicaoAvaliacoes().getOrDefault("RUIM", 0.0).intValue();

        String boa = String.valueOf(votosBom);
        String regular = String.valueOf(votosMedio);
        String ruim = String.valueOf(votosRuim);

        view.atualizarDados(total, media, percentuaisQuestoes, boa, regular, ruim);
    }

    private void configurarAcoes() {
        view.addVoltarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                new Inicio().setVisible(true);
            }
        });

        view.addAvancarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alerta = estatisticas.identificarAlertaAprendizado();
                JOptionPane.showMessageDialog(null, alerta, "Análise de Desempenho", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void exibirRelatorio() {
        view.setVisible(true);
    }
}