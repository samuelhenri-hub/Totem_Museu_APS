package br.com.descompila.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pesquisa extends JFrame {

    private JPanel painelConteudo;
    private JTextField campNome;
    private JButton btnVoltar;
    private JButton btnProximoNome;
    private JButton[] btnProximoPergunta = new JButton[5];
    private JCheckBox[] chkVerdadeiro = new JCheckBox[5];
    private JCheckBox[] chkFalso = new JCheckBox[5];
    private JButton btnBom, btnRegular, btnRuim, btnResultadoExperiencia;
    private JButton btnFecharResultado;
    
    // Variáveis para a tela de resultado (Atualizadas pelo Controller)
    private JLabel lblParabens;
    private JLabel lblResultado;
    private JLabel lblMensagem;
    private JLabel lblExperiencia;
    private JPanel painelGabarito;

    public Pesquisa(String[] perguntas) { // O Controller manda as perguntas ao criar a tela
        setTitle("Pesquisa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 20, 10));

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(new Color(60, 30, 10));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        btnVoltar = new JButton("← VOLTAR");
        estilizarBotao(btnVoltar);
        cabecalho.add(btnVoltar, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("PESQUISA - Robôs em Marte", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Century Gothic", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(255, 200, 100));
        cabecalho.add(lblTitulo, BorderLayout.CENTER);

        add(cabecalho, BorderLayout.NORTH);

        painelConteudo = new JPanel(new CardLayout());
        painelConteudo.setBackground(new Color(30, 20, 10));
        add(painelConteudo, BorderLayout.CENTER);

        painelConteudo.add(criarEtapaNome(), "etapa0");

        for (int i = 0; i < perguntas.length; i++) {
            painelConteudo.add(criarEtapaPergunta(i, perguntas[i]), "etapa" + (i + 1));
        }

        painelConteudo.add(criarEtapaExperiencia(), "etapa6");
        
        // A etapa de resultado começa vazia, o Controller a preenche depois
        painelConteudo.add(criarEtapaResultadoVazia(), "resultado");

        mostrarEtapa(0); // A tela inicializa mostrando o Card 0
    }

    public void mostrarEtapa(int etapa) {
        CardLayout cl = (CardLayout) painelConteudo.getLayout();
        if (etapa < 7) {
            cl.show(painelConteudo, "etapa" + etapa);
        } else {
            cl.show(painelConteudo, "resultado");
        }
    }

    private JPanel criarEtapaNome() {
        // ... (Todo o layout visual e o teclado virtual continuam 100% IGUAIS) ...
        JPanel painel = new JPanel(new BorderLayout(0, 10));
        painel.setBackground(new Color(40, 25, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel topo = new JPanel(new GridBagLayout());
        topo.setBackground(new Color(40, 25, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblIndicador = new JLabel("Etapa 1 de 7", SwingConstants.CENTER);
        lblIndicador.setFont(new Font("Century Gothic", Font.ITALIC, 12));
        lblIndicador.setForeground(new Color(180, 130, 70));
        gbc.gridy = 0;
        topo.add(lblIndicador, gbc);

        JLabel lblPergunta = new JLabel("<html><div style='text-align:center'>Qual é o seu nome?</div></html>", SwingConstants.CENTER);
        lblPergunta.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblPergunta.setForeground(Color.WHITE);
        gbc.gridy = 1;
        topo.add(lblPergunta, gbc);

        campNome = new JTextField();
        campNome.setFont(new Font("Century Gothic", Font.BOLD, 18));
        campNome.setHorizontalAlignment(JTextField.CENTER);
        campNome.setBackground(new Color(60, 40, 15));
        campNome.setForeground(Color.WHITE);
        campNome.setCaretColor(Color.WHITE);
        campNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 100, 50), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campNome.setPreferredSize(new Dimension(400, 42));
        campNome.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) { e.consume(); }
            @Override public void keyPressed(KeyEvent e) { e.consume(); }
            @Override public void keyReleased(KeyEvent e) { e.consume(); }
        });
        gbc.gridy = 2;
        topo.add(campNome, gbc);
        painel.add(topo, BorderLayout.NORTH);

        JPanel teclado = new JPanel(new GridBagLayout());
        teclado.setBackground(new Color(50, 30, 10));
        teclado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 100, 50), 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        GridBagConstraints gbcT = new GridBagConstraints();
        gbcT.fill = GridBagConstraints.BOTH;
        gbcT.insets = new Insets(3, 3, 3, 3);
        gbcT.weightx = 1;
        gbcT.weighty = 1;

        String[][] linhas = {
            {"Q","W","E","R","T","Y","U","I","O","P"},
            {"A","S","D","F","G","H","J","K","L"},
            {"Z","X","C","V","B","N","M"}
        };

        for (int row = 0; row < linhas.length; row++) {
            for (int col = 0; col < linhas[row].length; col++) {
                String letra = linhas[row][col];
                JButton btn = criarBotaoTeclado(letra, new Color(80, 50, 20));
                btn.addActionListener(e -> campNome.setText(campNome.getText() + letra));
                gbcT.gridx = col;
                gbcT.gridy = row;
                gbcT.gridwidth = 1;
                teclado.add(btn, gbcT);
            }
        }

        JButton btnEspaco = criarBotaoTeclado("ESPACO", new Color(60, 40, 15));
        btnEspaco.addActionListener(e -> campNome.setText(campNome.getText() + " "));
        gbcT.gridx = 0;
        gbcT.gridy = 3;
        gbcT.gridwidth = 5;
        teclado.add(btnEspaco, gbcT);

        JButton btnApagar = criarBotaoTeclado("APAGAR", new Color(140, 40, 20));
        btnApagar.addActionListener(e -> {
            String atual = campNome.getText();
            if (!atual.isEmpty()) {
                campNome.setText(atual.substring(0, atual.length() - 1));
            }
        });
        gbcT.gridx = 5;
        gbcT.gridy = 3;
        gbcT.gridwidth = 5;
        teclado.add(btnApagar, gbcT);

        painel.add(teclado, BorderLayout.CENTER);

        // AQUI MUDA: O botão é criado, mas a lógica (actionListener) sai daqui!
        btnProximoNome = new JButton("PRÓXIMA →");
        estilizarBotao(btnProximoNome);

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(new Color(40, 25, 10));
        painelBotao.add(btnProximoNome);
        painel.add(painelBotao, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarEtapaPergunta(int indice, String perguntaTexto) {
        JPanel painel = new JPanel(new GridBagLayout());
        // ... (Layout visual das perguntas) ...
        painel.setBackground(new Color(40, 25, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblIndicador = new JLabel("Etapa " + (indice + 2) + " de 7", SwingConstants.CENTER);
        lblIndicador.setFont(new Font("Century Gothic", Font.ITALIC, 12));
        lblIndicador.setForeground(new Color(180, 130, 70));
        gbc.gridy = 0;
        painel.add(lblIndicador, gbc);

        JLabel lblPergunta = new JLabel(
                "<html><div style='text-align:center'>" + perguntaTexto.replace("\n", "<br>") + "</div></html>",
                SwingConstants.CENTER
        );
        lblPergunta.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblPergunta.setForeground(Color.WHITE);
        gbc.gridy = 1;
        painel.add(lblPergunta, gbc);

        JPanel painelOpcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        painelOpcoes.setBackground(new Color(40, 25, 10));

        chkVerdadeiro[indice] = new JCheckBox("( ) VERDADEIRO");
        chkVerdadeiro[indice].setFont(new Font("Century Gothic", Font.BOLD, 14));
        chkVerdadeiro[indice].setForeground(new Color(100, 220, 100));
        chkVerdadeiro[indice].setBackground(new Color(40, 25, 10));
        chkVerdadeiro[indice].setFocusPainted(false);

        chkFalso[indice] = new JCheckBox("( ) FALSO");
        chkFalso[indice].setFont(new Font("Century Gothic", Font.BOLD, 14));
        chkFalso[indice].setForeground(new Color(220, 100, 100));
        chkFalso[indice].setBackground(new Color(40, 25, 10));
        chkFalso[indice].setFocusPainted(false);

        // Isso é lógica de tela, pode ficar aqui (um desmarca o outro)
        chkVerdadeiro[indice].addActionListener(e -> {
            if (chkVerdadeiro[indice].isSelected()) chkFalso[indice].setSelected(false);
        });
        chkFalso[indice].addActionListener(e -> {
            if (chkFalso[indice].isSelected()) chkVerdadeiro[indice].setSelected(false);
        });

        painelOpcoes.add(chkVerdadeiro[indice]);
        painelOpcoes.add(chkFalso[indice]);
        gbc.gridy = 2;
        painel.add(painelOpcoes, gbc);

        // Botão criado, mas sem lógica
        btnProximoPergunta[indice] = new JButton("PRÓXIMA →");
        estilizarBotao(btnProximoPergunta[indice]);
        gbc.gridy = 3;
        painel.add(btnProximoPergunta[indice], gbc);

        return painel;
    }

    private JPanel criarEtapaExperiencia() {
        JPanel painel = new JPanel(new GridBagLayout());
        // ... (Layout visual da experiência) ...
        painel.setBackground(new Color(40, 25, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblIndicador = new JLabel("Etapa 7 de 7", SwingConstants.CENTER);
        lblIndicador.setFont(new Font("Century Gothic", Font.ITALIC, 12));
        lblIndicador.setForeground(new Color(180, 130, 70));
        gbc.gridy = 0;
        painel.add(lblIndicador, gbc);

        JLabel lblPergunta = new JLabel("Como foi sua experiência no museu?", SwingConstants.CENTER);
        lblPergunta.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblPergunta.setForeground(Color.WHITE);
        gbc.gridy = 1;
        painel.add(lblPergunta, gbc);

        JPanel painelOpcoes = new JPanel(new GridLayout(3, 1, 10, 10));
        painelOpcoes.setBackground(new Color(40, 25, 10));

        btnBom = new JButton("BOM");
        btnRegular = new JButton("REGULAR");
        btnRuim = new JButton("RUIM");

        estilizarBotao(btnBom);
        estilizarBotao(btnRegular);
        estilizarBotao(btnRuim);
        
        // Lógica visual da tela (marcar com ✓)
        btnBom.addActionListener(e -> { btnBom.setText("✓ BOM"); btnRegular.setText("REGULAR"); btnRuim.setText("RUIM"); });
        btnRegular.addActionListener(e -> { btnBom.setText("BOM"); btnRegular.setText("✓ REGULAR"); btnRuim.setText("RUIM"); });
        btnRuim.addActionListener(e -> { btnBom.setText("BOM"); btnRegular.setText("REGULAR"); btnRuim.setText("✓ RUIM"); });

        painelOpcoes.add(btnBom);
        painelOpcoes.add(btnRegular);
        painelOpcoes.add(btnRuim);
        gbc.gridy = 2;
        painel.add(painelOpcoes, gbc);

        // Botão criado, mas sem lógica
        btnResultadoExperiencia = new JButton("VER RESULTADO →");
        estilizarBotao(btnResultadoExperiencia);
        gbc.gridy = 3;
        painel.add(btnResultadoExperiencia, gbc);

        return painel;
    }

    private JPanel criarEtapaResultadoVazia() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(40, 25, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        lblParabens = new JLabel("", SwingConstants.CENTER);
        lblParabens.setFont(new Font("Century Gothic", Font.BOLD, 22));
        lblParabens.setForeground(new Color(255, 200, 100));
        gbc.gridy = 0;
        painel.add(lblParabens, gbc);

        lblResultado = new JLabel("", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblResultado.setForeground(Color.WHITE);
        gbc.gridy = 1;
        painel.add(lblResultado, gbc);

        lblMensagem = new JLabel("", SwingConstants.CENTER);
        lblMensagem.setFont(new Font("Century Gothic", Font.ITALIC, 14));
        lblMensagem.setForeground(new Color(180, 220, 180));
        gbc.gridy = 2;
        painel.add(lblMensagem, gbc);

        lblExperiencia = new JLabel("", SwingConstants.CENTER);
        lblExperiencia.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblExperiencia.setForeground(new Color(255, 200, 100));
        gbc.gridy = 3;
        painel.add(lblExperiencia, gbc);

        painelGabarito = new JPanel(new GridLayout(5, 1, 5, 5));
        painelGabarito.setBackground(new Color(60, 35, 15));
        painelGabarito.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 100, 50), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        gbc.gridy = 4;
        painel.add(painelGabarito, gbc);

        btnFecharResultado = new JButton("FECHAR");
        estilizarBotao(btnFecharResultado);
        gbc.gridy = 5;
        painel.add(btnFecharResultado, gbc);

        return painel;
    }

    // ==========================================================
    // MÉTODOS DE CONEXÃO COM O CONTROLLER (Tomadas)
    // ==========================================================

    public String getNomeDigitado() { return campNome.getText().trim(); }
    
    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE); }

    public void addVoltarListener(ActionListener listener) { btnVoltar.addActionListener(listener); }
    public void addProximoNomeListener(ActionListener listener) { btnProximoNome.addActionListener(listener); }
    public void addProximoPerguntaListener(int indice, ActionListener listener) { btnProximoPergunta[indice].addActionListener(listener); }
    public void addVerResultadoListener(ActionListener listener) { btnResultadoExperiencia.addActionListener(listener); }
    public void addFecharResultadoListener(ActionListener listener) { btnFecharResultado.addActionListener(listener); }
    public void addExperienciaListener(ActionListener listener) {
        btnBom.addActionListener(listener);
        btnRegular.addActionListener(listener);
        btnRuim.addActionListener(listener);
    }

    public Boolean getRespostaSelecionada(int indice) {
        if (chkVerdadeiro[indice].isSelected()) return true;
        if (chkFalso[indice].isSelected()) return false;
        return null;
    }

    public void atualizarTelaResultado(String nome, int acertos, String experiencia, boolean[] gabarito, Boolean[] respostasUsuarios) {
        lblParabens.setText("🎉 Parabéns, " + nome + "!");
        lblResultado.setText("Você acertou " + acertos + " de 4 perguntas!");
        lblExperiencia.setText("Experiência: " + experiencia);

        String mensagem;
        if (acertos == 4) mensagem = "Incrível! Você é um expert em Robôs em Marte! 🚀";
        else if (acertos >= 3) mensagem = "Muito bem! Você conhece bastante sobre o tema!";
        else if (acertos == 2) mensagem = "Bom esforço! Vale rever as obras com calma.";
        else mensagem = "Continue explorando o tema! Marte tem muito a ensinar.";
        
        lblMensagem.setText("<html><div style='text-align:center'>" + mensagem + "</div></html>");

        painelGabarito.removeAll();
        for (int i = 0; i < 4; i++) {
            boolean acertou = (respostasUsuarios[i] != null && respostasUsuarios[i] == gabarito[i]);
            String icone = acertou ? "✅" : "❌";
            String respCorreta = gabarito[i] ? "Verdadeiro" : "Falso";
            JLabel lbl = new JLabel(icone + " Pergunta " + (i + 1) + ": " + respCorreta);
            lbl.setFont(new Font("Century Gothic", Font.PLAIN, 13));
            lbl.setForeground(acertou ? new Color(100, 220, 100) : new Color(220, 100, 100));
            painelGabarito.add(lbl);
        }
        
        JLabel lblExp = new JLabel("📊 Avaliação: " + experiencia); 
        painelGabarito.add(lblExp);

        painelGabarito.revalidate();
        painelGabarito.repaint();
    }

    
    private JButton criarBotaoTeclado(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        Color hover = cor.brighter();
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            @Override public void mouseExited(MouseEvent e) { btn.setBackground(cor); }
        });
        return btn;
    }

    private void estilizarBotao(JButton btn) {
        btn.setFont(new Font("Century Gothic", Font.BOLD, 14));
        btn.setBackground(new Color(180, 80, 30));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(200, 40));
    }
}