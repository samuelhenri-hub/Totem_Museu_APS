package br.com.descompila.Controller;

import br.com.descompila.Models.Obra;
import br.com.descompila.View.Obras;
import br.com.descompila.View.TelaObraDetalhe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ObrasController {

    private List<Obra> listaDeObras;
    private TelaObraDetalhe viewDetalhe;

    public ObrasController() {
        this.listaDeObras = new ArrayList<>();
        carregarObras();
    }

    private void carregarObras() {
        
        listaDeObras.add(new Obra("Operação Gênese: O Arquiteto de Ferro", 
            "A obra retrata um robô de carga instalando os primeiros módulos habitacionais.\nA pintura destaca a força e a precisão das máquinas, mostrando que a presença humana em Marte começa, obrigatoriamente, pelas mãos metálicas de seus precursores robóticos.", 
            "/imagens/obra1G.png"));
  
        listaDeObras.add(new Obra("Patrulha das Sombras no Cânion", 
            "Os drones de Patrulha das Sombras no Cânion têm a função de proteger e monitorar as áreas inexploradas do planeta vermelho.", 
            "/imagens/obra2G.png")); 

        listaDeObras.add(new Obra("O Andarilho do Rio de Pedras", 
            "A pintura O Andarilho do Rio de Pedras mostra um rover moderno explorando leitos secos onde outrora corria água marciana.", 
            "/imagens/obra3G.png"));

        listaDeObras.add(new Obra("Reunião de Gerações Metálicas", 
            "De acordo com a obra Reunião de Gerações Metálicas, o encontro entre robôs de diferentes épocas celebra a durabilidade e a evolução da engenharia espacial.", 
            "/imagens/obra4G.png"));

        listaDeObras.add(new Obra("O Guardião do Primeiro Broto", 
            "Na obra O Guardião do Primeiro Broto, o robô é retratado cuidando meticulosamente de uma estufa experimental, simbolizando a esperança de vida.", 
            "/imagens/obra5G.png"));

        listaDeObras.add(new Obra("Sinfonia das Engrenagens", 
            "Uma representação artística da sincronia perfeita entre as máquinas mineradoras operando nas crateras mais profundas de Marte.", 
            "/imagens/obra6G.png"));

        listaDeObras.add(new Obra("O Observador Silencioso", 
            "Esta obra mostra um satélite caído que foi reaproveitado como uma torre de observação estática, vigiando as tempestades de areia.", 
            "/imagens/obra7G.png"));

        listaDeObras.add(new Obra("Alvorecer Metálico", 
            "O reflexo do primeiro sol da manhã marciana batendo nas placas solares da base principal, garantindo energia para mais um dia de exploração.", 
            "/imagens/obra8G.png"));

        listaDeObras.add(new Obra("Caminhos de Poeira e Titânio", 
            "As marcas deixadas pelas esteiras dos rovers na areia vermelha, traçando os primeiros mapas físicos feitos por máquinas.", 
            "/imagens/obra9G.png"));

        listaDeObras.add(new Obra("O Último Comando", 
            "Um tributo ao primeiro rover enviado a Marte, descansando eternamente após cumprir sua missão e enviar seus últimos dados para a Terra.", 
            "/imagens/obra10G.png"));
    }

    public void abrirObra(int indice) {
        Obra obraSelecionada = listaDeObras.get(indice);
        
        viewDetalhe = new TelaObraDetalhe();
        
        viewDetalhe.preencherDadosDaObra(
            obraSelecionada.getTitulo(), 
            obraSelecionada.getDescricao(), 
            obraSelecionada.getCaminhoImagem()
        );

        viewDetalhe.addVoltarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDetalhe.dispose(); 
                new Obras().setVisible(true);
            }
        });

        viewDetalhe.addProximaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDetalhe.dispose(); 
                
                int proximoIndice = (indice + 1) % listaDeObras.size(); 
                
                abrirObra(proximoIndice);
            }
        });

        viewDetalhe.setVisible(true);
    }
}
