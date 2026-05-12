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
            "A obra retrata drones autônomos realizando o mapeamento topográfico de fendas profundas.\nO foco aqui é a agilidade robótica, mostrando como essas sentinelas voadoras são os olhos da humanidade em lugares onde o homem ainda não pode pisar.", 
            "/imagens/obra2G.png")); 
<<<<<<< HEAD
            
        // Obra 2
        listaDeObras.add(new Obra("Extrator de Segredos Milenares", 
            "A obra retrata drones autônomos realizando o mapeamento topográfico de fendas profundas.\n O foco aqui é a agilidade robótica, mostrando como essas sentinelas voadoras são os olhos da humanidade em lugares onde o homem ainda não pode pisar.", 
            "/imagens/obra3G.png"));

        // Obra 3
        listaDeObras.add(new Obra("O Guardião do Primeiro Broto", 
            "A obra retrata um rover de pesquisa botânica cuidando de uma estufa experimental.\n A tela mostra o robô não apenas como uma ferramenta, mas como um protetor da vida, criando um elo visual entre a frieza do metal e a vibração do primeiro verde em Marte.", 
            "/imagens/obra4G.png"));

        // Obra 4
        listaDeObras.add(new Obra("Expedição no Vale dos Prismas", 
            "Esta tela retrata uma unidade de exploração geológica atravessando um campo de cristais gigantes.\n A obra foca na capacidade sensorial dos robôs de identificar beleza e ciência em formações minerais que brilham sob o céu alaranjado.", 
            "/imagens/obra5G.png"));

        // Obra 5
        listaDeObras.add(new Obra("Reunião de Gerações Metálicas", 
            "A obra retrata o encontro de dois robôs exploradores de diferentes épocas no topo de uma cratera.\n A pintura celebra a durabilidade da engenharia robótica, tratando o encontro como uma conversa silenciosa sobre décadas de dados coletados.", 
            "/imagens/obra6G.png"));

        // Obra 6
        listaDeObras.add(new Obra("O Andarilho do Rio de Pedras", 
            "Esta obra retrata um rover solitário atravessando o leito de um rio seco há bilhões de anos.\n A imagem evoca a resiliência do robô, que continua sua jornada incansável por terrenos áridos em busca de vestígios de um passado aquático.", 
            "/imagens/obra7G.png"));

        // Obra 7
        listaDeObras.add(new Obra("Desafio à Gravidade: O Escalador", 
            "A pintura retrata um robô de tração especial subindo uma encosta íngreme e perigosa.\n A obra retrata a superação técnica, mostrando que, para um robô projetado para a exploração, não existem barreiras físicas intransponíveis em Marte.", 
            "/imagens/obra8G.png"));

        // Obra 8
        listaDeObras.add(new Obra("O Elo Digital: Da Terra ao Vermelho", 
            "A pintura retrata um robô de tração especial subindo uma encosta íngreme e perigosa.\n A obra retrata a superação técnica, mostrando que, para um robô projetado para a exploração, não existem barreiras físicas intransponíveis em Marte.", 
            "/imagens/obra9G.png"));

        // Obra 9
        listaDeObras.add(new Obra("Ecos da Noite Em Marte", 
            "Obra inspirada na exploração espacial e nas missões em Marte.\r\n" + //
                                "Retrata a curiosidade humana e o avanço da tecnologia diante do desconhecido. Une o clássico e o futurista, mostrando que a busca por conhecimento atravessa o tempo.",
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
