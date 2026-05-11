package br.com.descompila.Controller;

import br.com.descompila.View.Pesquisa;
import br.com.descompila.View.Inicio;

import br.com.descompila.Models.Questao;
import br.com.descompila.Models.SessaoPsquisa;
import br.com.descompila.Models.MemoriaDados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;

public class PesquisaController {

    private Pesquisa view;
   
    private String nomeUsuario = "";
    private String experienciaUsuario = "";
    private Boolean[] respostasUsuario = new Boolean[5];
    private final boolean[] gabarito = {true, false, false, true, false};
    
    private final String[] perguntas = {
        "Na obra O Guardião do Primeiro Broto, o robô é retratado\ncuidando de uma estufa experimental.",
        "Os drones de Patrulha das Sombras no Cânion têm a função\nde transportar módulos habitacionais para os humanos.",
        "A pintura O Andarilho do Rio de Pedras mostra um rover\natravessando um rio que ainda possui água corrente.",
        "De acordo com a obra Reunião de Gerações Metálicas, o encontro\nentre robôs de diferentes épocas celebra a durabilidade da engenharia.",
        "Na obra O Guardião do Primeiro Broto, o robô é retratado\napenas como uma máquina agrícola comum."
    };

    public PesquisaController() {
        this.view = new Pesquisa(perguntas);
        configurarAcoes();
    }

    private void configurarAcoes() {
   
        view.addVoltarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                new Inicio().setVisible(true);
            }
        });

        view.addProximoNomeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = view.getNomeDigitado();
                if (nome.isEmpty()) {
                    view.exibirMensagem("Por favor, digite seu nome!");
                } else {
                    nomeUsuario = nome; 
                    view.mostrarEtapa(1); 
                }
            }
        });

        for (int i = 0; i < perguntas.length; i++) {
            final int indicePergunta = i; 
            
            view.addProximoPerguntaListener(indicePergunta, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Boolean resposta = view.getRespostaSelecionada(indicePergunta);
                    
                    if (resposta == null) {
                        view.exibirMensagem("Selecione Verdadeiro ou Falso!");
                    } else {
                        respostasUsuario[indicePergunta] = resposta; 
                        
                        if (indicePergunta == 4) { 
                            view.mostrarEtapa(6); 
                        } else {
                            view.mostrarEtapa(indicePergunta + 2); 
                        }
                    }
                }
            });
        }

        view.addExperienciaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botaoClicado = (JButton) e.getSource();
                String texto = botaoClicado.getText();
                
                if (texto.contains("BOM")) experienciaUsuario = "Bom";
                else if (texto.contains("REGULAR")) experienciaUsuario = "Regular";
                else if (texto.contains("RUIM")) experienciaUsuario = "Ruim";
            }
        });

        view.addVerResultadoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (experienciaUsuario.isEmpty()) {
                    view.exibirMensagem("Escolha uma opção de experiência!");
                } else {
                    calcularEExibirResultado();
                }
            }
        });

        view.addFecharResultadoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                new Inicio().setVisible(true);
            }
        });
    }

    private void calcularEExibirResultado() {
        int acertos = 0;
        int erros = 0;

        for (int i = 0; i < 5; i++) {
            if (respostasUsuario[i] != null && respostasUsuario[i] == gabarito[i]) {
                acertos++;
            } else {
                erros++;
            }
        }

        Map<Questao, Integer> mapaRespostas = new HashMap<>();
        List<String> alternativasVF = Arrays.asList("Verdadeiro", "Falso");

        for (int i = 0; i < 5; i++) {
            int indiceCorreta = gabarito[i] ? 0 : 1; 
            
            Questao q = new Questao(i + 1, perguntas[i], alternativasVF, indiceCorreta, false);

            int indiceRespondido = (respostasUsuario[i] != null && respostasUsuario[i]) ? 0 : 1;
            
            mapaRespostas.put(q, indiceRespondido);
        }

        List<String> alternativasExp = Arrays.asList("BOM", "MEDIO", "RUIM");
        Questao qExp = new Questao(6, "Avaliação da Exposição", alternativasExp, -1, true);

        int indiceExp = 0;
        String notaFormatada = "BOM";
        
        if (experienciaUsuario.equalsIgnoreCase("Regular")) {
            indiceExp = 1;
            notaFormatada = "MEDIO"; 
        } else if (experienciaUsuario.equalsIgnoreCase("Ruim")) {
            indiceExp = 2;
            notaFormatada = "RUIM";
        }
        
        mapaRespostas.put(qExp, indiceExp);

        SessaoPsquisa novaSessao = new SessaoPsquisa(mapaRespostas, acertos, erros, notaFormatada);
        MemoriaDados.sessoesRealizadas.add(novaSessao);

        view.atualizarTelaResultado(nomeUsuario, acertos, experienciaUsuario, gabarito, respostasUsuario);
        view.mostrarEtapa(7); 
    }

    public void iniciarPesquisa() {
        view.setVisible(true);
    }
}
